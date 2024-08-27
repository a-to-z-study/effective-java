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

<br>

## Item 17: 변경 가능성을 최소화하라

---

<br>

**불변 클래스로 만드는 방법**

- 객체의 상태를 변경하는 메서드(변경자)를 제공하지 않는다.
- 클래스를 확장할 수 없도록 한다. (final class)
- 모든 필드를 final로 선언한다.
- 모든 필드를 private으로 선언하여 필드가 참조하는 가변 객체를 직접 수정하지 못하게 한다.
- 자신 외에는 내부의 가변 컴포넌트에 접근할 수 없도록 한다. (필드를 그대로 반환하지 않고 방어적 복사)

<br>

**불변 객체의 장점**

- 불변 객체는 근본적으로 스레드 안전하여 동기화할 필요가 없다.
  - 공유, 재활용 이점
- 가변 객체에 비해 GC 성능상의 이점이 있을 수 있다.
  - 객체 생성에 대한 비용은 과대평가되고 있으며, 이는 불변 객체를 이용한 효율로 충분히 상쇄할 수 있다.
  - GC는 새롭게 생성된 객체는 금방 죽는다는 Weak Generational Hypothesis 가설에 맞춰 설계 
  - 불변 객체를 새로 생성한다 해도 GC는 생명주기가 짧은 객체 처리에 대한 부담이 적다.
  - 불변 객체를 이용하면 GC의 스캔 빈도와 범위가 줄게 되어 GC의 성능에 도움이 된다 
  - 가변 객체는 일반적으로 상태를 변경하는 형식으로 사용해서 불변 객체보다 생명 주기가 길고 지속적으로 GC의 스캔 범위에 포함되어 불변 객체보다 성능상 이점이 적다.

<br>

BigInteger, BigDecimal의 메서드는 `final`이 아니라 `Override`가 가능하다.
신뢰할 수 없는 클라이언트로부터 BigDecimal의 인스턴스를 인수로 받는다면 주의해야 한다.

```java
public static BigInteger safeInstance(BigInteger val) {
	return val.getClass() == BigInteger.class ? val : new BigInteger(val.toByteArray());
}
```

<br>

## Item 18: 상속보다는 컴포지션을 이용하라

---

상속의 단점

- 캡슐화를 깨뜨린다.
  - 상위 클래스는 릴리스마다 내부 구현이 달라질 수 있으며, 그 여파로 하위 클래스가 오동작할 수 있다.

상속은 반드시 하위 클래스가 상위 클래스의 진짜 하위 타입인 상황에서만 쓰여야 한다.
- `is-a` 관계일 때만 상속
  - 이 원칙을 위반한 클래스 (`Stack`은 `Vector`를 확장)
- 컴포지션을 써야 할 상황에서 상속을 사용하는 건 내부 구현을 불필요하게 노출하는 꼴

<br>

## Item 20: 추상 클래스보다는 인터페이스를 우선하라

---

<br>

**자바가 제공하는 다중 구현 메커니즘**
- 인터페이스
- 추상 클래스

추상 클래스가 정의한 타입을 구현하는 클래스는 반드시 추상 클래스의 하위 클래스가 되어야 한다.
자바는 단일 상속만 지원(다이아몬드 문졔)하니, 추상 클래스 방식은 새로운 타입을 정의하는 데 커다란 제약을 안게 되는 셈이다.

반면 인터페이스가 선언한 메서드를 모두 정의하고 그 일반 규약을 잘 지킨 클래스라면 다른 어떤 클래스를 상속했든 같은 타입으로 취급된다.

인터페이스 default method 다중 상속 문제

```java
public class Test implements TestA, TestB{
	// TestA, TestB 의 default method가 같은 경우
	@Override
	public void testDefault() {
		TestA.super.testDefault();
	}
}
```

<br>

## Item 22: 인터페이스는 타입을 정의하는 용도로만 사용하라

---

인터페이스를 상수를 뜻하는 `static final` 필드로만 가득 찬 인터페이스로 만드는 것은 안티 패턴

필요하다면 상수 유틸리티 클래스를 만들자