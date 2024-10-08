## Item 1: 생성자 대신 정적 팩터리 메서드를 고려하라.

정적 팩터리 메서드가 생성자보다 좋은 점 5가지
1. 이름을 가질 수 있다. (반환될 객체의 특성을 쉽게 묘사할 수 있다.)
2. (필요에 따라) 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
   - 플라이웨이트(Flyweight) 패턴도 이와 비슷한 기법
3. 반환 타입의 하위 타입 객체를 반환할 수 있다.
4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.

하나의 시그니처로는 하나의 생성자만 만들 수 있다.
- 매개변수 순서를 다르게 한 생성자를 추가할 수 있지만 각 생성자의 의도를 모르면 실수를 할 수 있다.

플라이웨이트 패턴
- 재사용 가능한 객체 인스턴스를 공유하여 메모리 사용량을 최소화하는 구조 패턴
- 동일하거나 유사한 객체들 사이에 가능한 공유 가능한 데이터를 공유하도록 하여 최적화를 노리는 경량 패턴이라고도 불린다.
- 자바 적용 사례
  - String 클래스는 Flyweight 패턴을 통해 `String Constant Pool`로 리터럴 문자열 데이터에 대한 캐싱

`Boolean.valueOf(boolean)` 메서드는 객체를 아예 생성하지 않는다.  
Boolean 클래스는 `true`와 `false` 값을 나타내기 위해 캐싱된 두 개의 정적 상수 객체를 제공한다.
- Boolean.TRUE
- Boolean.FALSE

```java
Boolean b1 = Boolean.valueOf(true);
Boolean b2 = Boolean.valueOf(true);

System.out.println(b1 == b2); // true
```

`Integer.valueOf()`, `Double.valueOf()` 같은 Wrapper 클래스들도 마찬가지로 적용


다음은 정적 팩터리 메서드에 흔히 사용하는 명명 방식들이다.
- from: 매개변수를 하나 받아서 인스턴스를 반환하는 형변환 메서드
- of: 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메서드

## Item 2: 생성자에 매개변수가 많다면 빌더를 고려하라.

1. 과거 프로그래머들은 점층적 생성자 패턴을 즐겨 사용했다.
   이런 생성자는 사용자가 설정하길 원치 않는 매개변수까지 포함하기 쉬운데, 어쩔 수 없이 그런 매개변수에도 값을 지정해줘야 한다.
   이는 매개변수 개수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵게 만든다.
2. 자바빈즈 패턴을 대안으로 사용하기도 했다.
   매개변수가 없는 생성자로 객체를 만든 후, 세터 메서드들을 호출해 원하는 매개변수의 값을 설정하는 방식이다.
   이러한 방식은 객체가 완전히 생성되기 전까지 일관성이 무너진 상태에 놓이게 된다는 단점을 갖는다.

점층적 생성자 패턴의 안전성과 자바빈즈 패턴의 가독성을 겸비한 빌더 패턴이 대안이 될 수 있다.
빌더는 생성할 클래스 안에 정적 멤버 클래스로 만들어두는 게 보통이다.  
ex) [NutritionFacts](NutritionFacts.java)

또한 빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기에 좋다.  
ex) [Pizza](Pizza.java)

다만 점층적 생성자 패턴보다는 코드가 장황해서 매개변수가 4개 이상은 되어야 값어치를 한다.
하지만 API는 시간이 지날수록 매개변수가 많아지는 경향이 있음을 명심하자.
생성자나 정적 팩터리 방식으로 시작했다가 나중에 매개변수가 많아지면 빌더 패턴으로 전환할 수도 있지만, 애초에 빌더로 시작하는 편이 나을 때가 많다.

## Item 3: private 생성자나 열거 타입으로 싱글턴임을 보증하라.

싱글턴이란 인스턴스를 오직 하나만 생성할 수 있는 클래스를 말한다.
그런데 클래스를 싱글턴으로 만들면 이를 사용하는 클라이언트를 테스트하기가 어려워질 수 있다.
타입을 인터페이스로 정의한 다음 그 인터페이스를 구현해서 만든 싱글턴이 아니라면 싱글턴 인스턴스를 가짜(mock) 구현으로 대체할 수 없기 때문이다.

싱글턴을 만드는 방식 3가지 
1. public static final 필드 방식의 싱글턴 ex) [PublicElvis](PublicElvis.java)
2. 정적 팩터리 방식의 싱글턴 ex) [PrivateElvis](PrivateElvis.java)
3. 열거 타입 방식의 싱글턴 ex) [EnumElvis](EnumElvis.java)

자바는 기본적으로 열거 타입의 인스턴스는 하나만 생성하여 관리하기 때문에 직렬화 문제, 리플렉션 공격, 스레드 안전성 보장 측면에서 장점을 갖는다.

## Item 4: 인스턴스화를 막으려면 private 생성자를 사용하라.

추상 클래스로 만드는 것으로는 인스턴스화를 막을 수 없다.
하위 클래스를 만들어 인스턴스화하면 그만이다.
이를 본 사용자는 상속해서 쓰라는 뜻으로 오해할 수 있으니 더 큰 문제다.

컴파일러가 기본 생성자를 만드는 경우는 오직 명시된 생성자가 없을 때 뿐이니 private 생성자를 추가하면 클래스의 인스턴스화를 막을 수 있다.

```java
public class UtilityClass {
    private UtilityClass() {  // 인스턴스화 방지
        throw new AssertionError();
    }
}
```
## Item 5: 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라.

많은 클래스가 하나 이상의 자원에 의존한다.
가령 맞춤법 검사기는 사전에 의존하는데, 이런 클래스를 정적 유틸리티 클래스로 구현한 모습을 드물지 않게 볼 수 있다.
```java
public class SpellChecker {
    private static final Lexion dictionary = ...;
	
    private SpellChecker() {} // 객체 생성 방지
    
    public static boolean isValid(String word) { ... }
}
```

비슷하게, 싱글턴으로 구현하는 경우도 흔하다.
```java
public class SpellChecker {
    private static final Lexion dictionary = ...;
	
    private SpellChecker() {} // 객체 생성 방지
    public static SpellChecker INSTANCE = new SpellChecker();
	
	public boolean isValid(String word) { ... }
}
```

두 방식 모두 사전을 단 하나만 사용한다고 가정한다는 점에서 그리 훌륭해 보이지 않는다.
실전에서는 언어별로 사전이 따로 있고 특수 어휘용 사전을 별도로 두기도 한다.
SpellChecker가 여러 사전을 사용할 수 있도록 만들어보자.

(필드에서 final 한정자를 제거하고 다른 사전으로 교체하는 메서드를 추가할 수 있지만, 이 방식은 어색하고 오류를 내기 쉬우며 멀티스레드 환경에서는 쓸 수 없다.)

인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨주는 의존 객체 주입은 유연성과 테스트 용이성을 높여준다.
```java
public class SpellChecker {
    private final Lexicon dictionary;
	
    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }
}
```

<br>

의존 객체 주입의 변형으로 생성자에 자원 팩터리를 넘겨주는 방식이 있다.
팩터리란 호출할 때마다 특정 타입의 인스턴스를 반복해서 만들어주는 객체를 말한다.
즉, 팩토리 메서드 패턴을 구현한 것
`Supplier<T>` 를 통해 팩터리를 넘길 수 있다.

- [Factory Method Pattern](https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9CFactory-Method-%ED%8C%A8%ED%84%B4-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EB%B0%B0%EC%9B%8C%EB%B3%B4%EC%9E%90)
- [Enum Factory Method](https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-Enum-Factory-Method-%EB%B3%80%ED%98%95-%ED%8C%A8%ED%84%B4)

## Item 6: 불필요한 객체 생성을 피하라.

똑같은 기능의 객체를 매번 생성하기 보다는 객체 하나를 재사용하는 편이 나을 때가 많다.
특히 생성 비용이 아주 비싼 객체가 반복해서 필요하다면 캐싱하여 재사용하는 것이 좋다.

`String.matches` 메서드는 정규표현식으로 문자혈 형태를 확인하는 가장 쉬운 방법이지만, 반복적으로 사용하기엔 적합하지 않다.
내부에서 만드는 Pattern 인스턴스의 생성 비용이 높아서 Pattern 인스턴스를 캐싱해두고 재사용하면 훨씬 효율적
`Pattern.macher().matches()`

```java
public class RomanNumerals {
	private static final Pattern ROMAN = Pattern.compile(
		"^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

	static boolean isRomanNumeralFast(String s) {
		return ROMAN.matcher(s).matches();
	}
}
```

오토박싱은 기본 타입과 그에 대응하는 박싱된 기본 타입의 구분을 흐려주지만, 완전히 없애주는 것은 아니다.
의미상으로는 별다를 것 없지만 성능에서는 그렇지 않다.
될 수 있으면 박싱된 기본 타입보다는 기본 타입을 사용하고, 의도치 않은 오토박싱이 숨어들지 않도록 주의하자.

이번 주제를 "객체 생성은 비싸니 피해야 한다"로 오해하면 안 된다.
특히나 요즘의 JVM에서는 별다른 일을 하지 않는 작은 객체를 생성하고 회수하는 일이 크게 부담되지 않는다.

또한 방어적 복사가 필요한 상황에서 객체를 재사용했을 때의 피해가 필요 없는 객체를 반복 생성했을 때의 피해보다 훨씬 크다는 사실을 기억하자.
방어적 복사에 실패하면 언제 터져 나올지 모르는 버그와 보안 구멍으로 이어지지만, 불필요한 객체 생성은 그저 코드 형태와 성능에만 영향을 준다.

## Item 7: 다 쓴 객체 참조를 해제하라.

일반적으로 자기 메모리를 직접 관리하는 클래스라면 프로그래머는 항시 메모리 누수에 주의해야 한다.
원소를 다 사용한 즉시 그 원소가 참조한 객체들을 null 처리해줘야 한다.

그렇다고 해서 모든 객체를 다 쓰자마자 일일이 null 처리할 필요는 없다.
이는 프로그램을 필요 이상으로 지저분하게 만든다.
다 쓴 참조를 해제하는 가장 좋은 방법은 그 참조를 담은 변수를 유효 범위(scope) 밖으로 밀어내는 것이다.
만약 변수의 범위를 최소가 되게 정의헀다면 이 일은 자연스럽게 이뤄진다.

객체 참조를 null 처리하는 일은 예외적인 경우여야 한다.
- 배열의 경우 사용하지 않는 영역(비활성)이 더 이상 쓸모없다는 것은 프로그래머의 생각으로 가비지 컬렉터는 알 수가 없다.

## Item 8: finalizer와 cleaner 사용을 피하라.

자바는 두 가지 객체 소멸자를 제공한다.
그중 finalizer는 예측할 수 없고, 상황에 따라 위험할 수 있어 일반적으로 불필요하다.
오동작, 낮은 성능, 이식성 문제의 원인이 되기 때문에 기본적으로 '쓰지 말아야' 한다.

그래서 자바 9에서는 finalizer를 사용 자제(deprecated) API로 지정하고 cleaner를 그 대안으로 소개했다.
cleaner는 finalizer보다는 덜 위험하지만, 여전히 예측할 수 없고, 느리고 일반적으로 불필요하다.

1. finalizer와 cleaner는 즉시 수행된다는 보장이 없다.  
즉, 제때 실행되어야 하는 작업은 절대 할 수 없다.
2. finalizer나 cleaner의 수행 시점뿐 아니라 수행 여부조차 보장하지 않는다.  
따라서 프로그램 생애 주기와 상관없는, 상태를 영구적으로 수정하는 작업에서는 절대 finalizer나 cleaner에 의존해서는 안된다.
3. finalizer 동작 중 발생한 예외는 무시되며, 처리할 작업이 남았더라도 그 순간 종료된다.  
잡지 못한 예외 때문에 해당 객체는 자칫 마무리가 덜 된 상태로 남을 수 있다.  
그리고 다른 스레드가 이처럼 훼손된 객체를 사용하려 한다면 어떻게 동작할지 예측할 수 없다.
4. finalizer와 cleaner는 심각한 성능 문제도 동반한다.
5. finalizer를 사용한 클래스는 finalizer 공격에 노출되어 심각한 보안 문제를 일으킬 수도 있다.

따라서 AutoCloseable을 구현해주고, 클라이언트에서 인스턴스를 다 쓰고 나면 close 메서드를 호출하면 된다(일반적으로 예외가 발생해도 제대로 종료되도록 try-with-resources를 사용해야 한다).

cleaner와 finalizer의 쓰임 두 가지는 다음과 같다.
1. 자원의 소유자가 close 메서드를 호출하지 않는 것에 대비한 안전망 역할
2. 가비지 컬렉터가 존재를 알지 못하는 네이티브 피어를 회수하지 않는 것에 대한 안전망 역할

[Cleaner 사용 예시](Room.java)

## Item 9: try-finally보다는 try-with-resource를 사용하라.

자바 라이브러리에는 close 메서드를 호출해 직접 닫아줘야 하는 자원이 많다.
InputStream, OutputStream, java.sql.Connection 등이 좋은 예다.
자원 닫기는 클라이언트가 놓치기 쉬워서 예측할 수 없는 성능 문제로 이어지기도 한다.
이런 자원 중 상당수가 안전망으로 finalizer를 활용하고는 있지는 finalizer는 그리 믿을만하지 못하다.

자원을 깔끔하게 닫기 위해 자바 7이 투척한 try-with-resources를 이용할 수 있다.
이 구조를 사용하려면 해당 자원이 AutoCloseable 인터페이스를 구현해야 하는데,
자바 라이브러리나 서드파티 라이브러리들의 수많은 클래스와 인터페이스가 이미 AutoCloseable을 구현하거나 확장해뒀다.
만약 닫아야 하는 자원을 뜻하는 클래스를 작성한다면 AutoCloseable을 반드시 구현하기 바란다.
