## Item 10: equals는 일반 규약을 지켜 재정의하라

---

**equals를 재정의하지 않아도 되는 경우**

- 각 인스턴스가 본질적으로 고유하다
  - 값을 표현하는 클래스(Integer, String ..)가 아니라 동작하는 개체를 표현하는 클래스 (ex. Thread)
- 인스턴스의 `logical equality`를 검사할 일이 없다.
- 상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어맞는다.
  - Set -> AbstratSet이 구현한 `equals`를 상속 (AbstracctList, AbstractMap도 동일)

**equals를 재정의해야 하는 경우**

- `logical equality`을 비교해야 하는 경우
  - 값을 표현하는 클래스는 객체가 같은지가 아니라 값이 같은지를 알고 싶어 할 것이다.
  - Map의 키와 Set의 원소로 사용
  - 1000원짜리 지폐 2장은 물리적으로는 다르지만 논리적으로 같은 값을 가진다.

Collection 클래스를 포함해 수 많은 클래스는 전달받은 객체가 equals 규약을 지킨다고 가정하고 동작한다.


**구체 클래스를 확장하는 경우 equals 문제점**

- 구체 클래스를 확장해 새로운 값을 추가하면서 equals 규약을 만족시킬 방법은 존재하지 않는다.
  - 예를 들어 좌표를 나타내는 Point 클래스를 상속받은 ColorPoint에 Color가 추가된다면 Point의 equals는 좌표만 비교하지만 ColorPoint의 equals는 Color도 비교해야 한다.

```java
// 대칭성 위배
@Override
public boolean equals(Object o) {
	// o가 Point 클래스면 항상 false
	if (!(o instanceOf ColorPoint)) {
		return false;
    }
	return super.equals(o) && ((ColorPoint) o).color == color;
}

Point p = new Point(1, 2);
ColorPoint cp = new ColorPoint(1, 2, Color.RED);

p.equals(cp) // true, 좌표만 비교
cp.equals(p) // false
```

```java
// 추이성 위배
@Override
public boolean equals(Object o) {
	if (!(o instanceOf Point)) {
		return false;
    }
	// o가 일반 Point면 색상 무시하고 비교
	if (!(o instanceOf ColorPoint)) {
	return o.equals(this);
	}
	// o가 ColorPoint면 색상까지 비교
	return super.equals(o) && ((ColorPoint) o).color == color;
}

ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
Point p2 = new Point(1, 2);
ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);

// 추이성 위배, p1, p2가 같고 p2, p3가 같으면 p1, p3도 같아야 한다.
p1.equals(p2) // true
p2.equals(p3) // true
p1.equals(p3) // false
```

<br>

**해결 방법**
- 상속 대신 컴포지션을 사용
- 상위 클래스를 추상 클래스로 선언하여 상위 클래스의 인스턴스 생성을 제한

```java
public class ColorPoint {
	private final Point point;
    private final Color color;
  
    // ...
	@Override
	public boolean equals(Object o) {
		if (!(o instanceOf ColorPoint)){
			return false;
		}
		ColorPoint cp = (ColorPoint)o;
		return cp.point.equals(point) && cp.color.equals(color);
	}
}
```

**자바 라이브러리에서 구체 클래스를 확장해 값을 추가한 클래스 사례**
- `java.sql.Timestamp`는 `java.util.Date` 를 확장한 후 nanoseconds 필드를 추가
  - Date 객체와 Timestamp 객체를 한 컬렉션에 넣거나 서로 섞어 사용하면 엉뚱하게 동작할 위험
- `java.net.URL`의 equals는 URL에 매핑된 호스트의 IP 주소를 이용해 비교하는데 호스트 이름을 IP 주소로 바꾸려면 네트워크를 통해야 하는데 그 결과가 항상 같다고 보장할 수 없다.
  - equals는 항시 메모리에 존재하는 객체만을 사용한 결정적 계산만 수행해야 한다.

<br>

**양질의 equals 메서드 구현 방법**

1. `==` 연산자를 사용해 입력이 자기 자신의 참조인지 확인
   - 단순한 성능 최적화용, 비교 작업이 복잡한 경우 좋음
2. `instanceof` 연산자로 타입 확인
   - 명시적 null 체크 대신 `instanceof` 를 통해 묵시적 null 체크
3. 비교
   - 기본 타입 필드 `==`
   - 참조 타입 필드 `equals`
   - flote, double 필드 `Float.compare(a, b)`, `Double.compare(a, b)` (부동소수값 때문)
     - `Float.equals`은 오토박싱을 수반할 수 있어서 성능상 좋지 않다.
   - 다를 가능성이 더 크거나 비교하는 비용이 싼 필드를 먼저 비교
4. equals를 재정의할 땐 hashCode도 반드시 재정의
5. Object 외의 타입을 매개변수로 받는 equals 메서드 선언하지 말자


**직접 구현하기보단 lombok의 `@EqualsAndHashCode`, 구글 AutoValue 프레임워크, IDE가 제공하는 기능 등을 활용**

(JPA를 사용하는 경우에는 주의)

<br>

## Item 11: equals를 재정의하려거든 hashCode도 재정의하라

---

equals를 재정의한 클래스에서 hashCode도 재정의해야 한다. 그렇지 않으면 HashMap, HashSet 같은 컬렉션의 원소로 사용할 때 문제를 일으킬 수 있다.

Object 명세중 "equals()가 같으면 두 객체의 hashCode는 같은 값을 반환해야 한다."
-> 논리적으로 같은 객체는 같은 hashCode를 반환해야 한다.

HashMap은 hashCode가 다른 entry끼리는 동치성 비교를 시도조차 하지 않도록 최적화되어 있다.

hashCode가 같으면 동일한 해시테이블 버킷에 담겨 평균 수행 시간이 O(1) -> O(n)으로 느려진다.


[[참고] equals와 hashCode는 왜 같이 재정의해야 할까?](https://tecoble.techcourse.co.kr/post/2020-07-29-equals-and-hashCode/)


## Item 12: toString을 항상 재정의하라

---

## Item 14: Comparable을 구현할지 고려하라

---

관계연산자 `<`, `>` 를 사용하는 이전 방식은 거추장스럽고 오류를 유발하니, 자바 7부터는 compare() 이용 권장


Comparator 구현 시 메서드 체이닝 방식으로 간결하게 사용 가능, 약간의 성능 저하

```java
Comparator<PhoneNumber> comparator = Comparator.comparingInt((PhoneNumber pn) -> pn.areaCode)
    .thenComparing(pn -> pn.prefix);
```