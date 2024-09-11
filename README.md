# Index

## Table of Contents

- [Chapter 2. 객체 생성과 파괴](#chapter-2-객체-생성과-파괴)
- [Chapter 3. 모든 객체의 공통 메서드](#chapter-3-모든-객체의-공통-메서드)
- [Chapter 4. 클래스와 인터페이스](#chapter-4-클래스와-인터페이스)
- [Chapter 5. 제네릭](#chapter-5-제네릭)
- [Chapter 6. 열거 타입과 에너테이션](#chapter-6-열거-타입과-애너테이션)
- [Chapter 7. 람다와 스트림](#chapter-7-람다와-스트림)
- [Chapter 8. 메서드](#chapter-8-메서드)
- [Chapter 9. 일반적인 프로그래밍 원칙](#chapter-9-일반적인-프로그래밍-원칙)
- [Chapter 10. 예외](#chapter-10-예외)
- [Chapter 11. 동시성](#chapter-11-동시성)
- [Chapter 12. 직렬화](#chapter-12-직렬화)

<br/>

## Chapter 2. 객체 생성과 파괴

- [x] Item 1: 생성자 대신 정적 팩터리 메서드를 고려하라
    - [docs](./src/main/java/me/java/chapter2/chapter2.md#item-1-생성자-대신-정적-팩터리-메서드를-고려하라)
- [x] Item 2: 생성자에 매개변수가 많다면 빌더를 고려하라
    - [docs](./src/main/java/me/java/chapter2/chapter2.md#item-2-생성자에-매개변수가-많다면-빌더를-고려하라)
    - [practice 1: Lombok의 `@Builder`와 직접 구현한 빌더 패턴 비교](./src/main/java/me/java/chapter2/practice/chapter2-practice.md#lombok의-builder와-직접-구현한-빌더-패턴-비교)
- [x] Item 3: private 생성자나 열거 타입으로 싱글턴임을 보증하라
    - [docs](./src/main/java/me/java/chapter2/chapter2.md#item-3-private-생성자나-열거-타입으로-싱글턴임을-보증하라)
- [x] Item 4: 인스턴스화를 막으려거든 private 생성자를 사용하라
    - [docs](./src/main/java/me/java/chapter2/chapter2.md#item-4-인스턴스화를-막으려면-private-생성자를-사용하라)
- [x] Item 5: 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라
    - [docs](./src/main/java/me/java/chapter2/chapter2.md#item-5-자원을-직접-명시하지-말고-의존-객체-주입을-사용하라)
- [x] Item 6: 불필요한 객체 생성을 피하라
    - [docs](./src/main/java/me/java/chapter2/chapter2.md#item-6-불필요한-객체-생성을-피하라)
- [x] Item 7: 다 쓴 객체 참조를 해제하라
    - [docs](./src/main/java/me/java/chapter2/chapter2.md#item-7-다-쓴-객체-참조를-해제하라)
- [x] Item 8: finalizer와 cleaner 사용을 피하라
    - [docs](./src/main/java/me/java/chapter2/chapter2.md#item-8-finalizer와-cleaner-사용을-피하라)
- [x] Item 9: try-finally보다는 try-with-resources를 사용하라
    - [docs](./src/main/java/me/java/chapter2/chapter2.md#item-9-try-finally보다는-try-with-resource를-사용하라)

<br/>

## Chapter 3. 모든 객체의 공통 메서드

- [x] Item 10: equals는 일반 규약을 지켜 재정의하라
    - [docs](./src/main/java/me/java/chapter3/chapter3.md#item-10-equals는-일반-규약을-지켜-재정의하라)
- [x] Item 11: equals를 재정의하려거든 hashCode도 재정의하라
    - [docs](./src/main/java/me/java/chapter3/chapter3.md#item-11-equals를-재정의하려거든-hashcode도-재정의하라)
- [x] Item 12: toString을 항상 재정의하라
  - [practice 1: `toString()`을 재정의하여 로깅에 활용하기](./src/main/java/me/java/chapter3/practice/tostring-practice.md)
- [x] Item 13: clone 재정의는 주의해서 진행하라
- [x] Item 14: Comparable을 구현할지 고려하라
    - [docs](./src/main/java/me/java/chapter3/chapter3.md#item-14-comparable을-구현할지-고려하라)

<br/>

## Chapter 4. 클래스와 인터페이스

- [x] Item 15: 클래스와 멤버의 접근 권한을 최소화하라
- [x] Item 16: public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라
- [x] Item 17: 변경 가능성을 최소화하라
    - [docs](./src/main/java/me/java/chapter4/chapter4.md#item-17-변경-가능성을-최소화하라)
- [x] Item 18: 상속보다는 컴포지션을 이용하라
    - [docs](./src/main/java/me/java/chapter4/chapter4.md#item-18-상속보다는-컴포지션을-이용하라)
- [x] Item 19: 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라.
- [x] Item 20: 추상 클래스보다는 인터페이스를 우선하라
    - [docs](./src/main/java/me/java/chapter4/chapter4.md#item-20-추상-클래스보다는-인터페이스를-우선하라)
- [x] Item 21: 인터페이스는 구현하는 쪽을 생각해 설계하라
- [x] Item 22: 인터페이스는 타입을 정의하는 용도로만 사용하라
    - [docs](./src/main/java/me/java/chapter4/chapter4.md#item-22-인터페이스는-타입을-정의하는-용도로만-사용하라)
- [x] Item 23: 태그 달린 클래스보다는 클래스 계층 구조를 활용하라
- [x] Item 24: 멤버 클래스는 되도록 static으로 만들라
- [x] Item 25: 톱레벨 클래스는 한 파일에 하나만 담아라

<br/>

## Chapter 5. 제네릭

- [x] Item 26: 로 타입은 사용하지 말라
  - [docs](./src/main/java/me/java/chapter5/chapter5.md#item-26-로-타입은-사용하지-말라)
- [x] Item 27: 비검사 경고를 제거하라
- [x] Item 28: 배열보다는 리스트를 사용하라
  - [docs](./src/main/java/me/java/chapter5/chapter5.md#item-28-배열보다는-리스트를-사용하라)
- [x] Item 29: 이왕이면 제네릭 타입으로 만들라
- [x] Item 30: 이왕이면 제네릭 메서드로 만들라
- [x] Item 31: 한정적 와일드카드를 사용해 API 유연성을 높이라
- [x] Item 32: 제네릭과 가변인수를 함께 쓸 때는 신중하라
- [x] Item 33: 타입 안전 이종 컨테이너를 고려하라

<br/>

## Chapter 6. 열거 타입과 애너테이션

- [x] Item 34: int 상수 대신 열거 타입을 사용하라
  - [docs](./src/main/java/me/java/chapter6/chapter6.md#item-34-int-상수-대신-열거-타입을-사용하라)
- [x] Item 35: ordinal 메서드 대신 인스턴스 필드를 사용하라
- [x] Item 36: 비트 필드 대신 EnumSet을 사용하라
  - [docs](./src/main/java/me/java/chapter6/chapter6.md#item-36-비트-필드-대신-enumset을-사용하라)
- [x] Item 37: ordinal 인덱싱 대신 EnumMap을 사용하라
- [x] Item 38: 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라
- [x] Item 39: 명명 패턴보다 애너테이션을 사용하라
- [x] Item 40: @Override 애너테이션을 일관되게 사용하라
  - [docs](./src/main/java/me/java/chapter6/chapter6.md#item-40-override-애너테이션을-일관되게-사용하라)
- [x] Item 41: 정의하려는 것이 타입이라면 마커 인터페이스를 사용하라
  - [docs](./src/main/java/me/java/chapter6/chapter6.md#item-41-정의하려는-것이-타입이라면-마커-인터페이스를-사용하라)

<br/>

## Chapter 7. 람다와 스트림

- [ ] Item 42: 익명 클래스보다는 람다를 사용하라
- [ ] Item 43: 람다보다는 메서드 참조를 사용하라
- [ ] Item 44: 표준 함수형 인터페이스를 사용하라
- [ ] Item 45: 스트림은 주의해서 사용하라
- [ ] Item 46: 스트림에서는 부작용 없는 함수를 사용하라
- [ ] Item 47: 반환 타입으로는 스트림보다 컬렉션이 낫다
- [ ] Item 48: 스트림 병렬화는 주의해서 적용하라

<br/>

## Chapter 8. 메서드

- [ ] Item 49: 매개변수가 유효한지 검사하라
- [ ] Item 50: 적시에 방어적 복사본을 만들라
- [ ] Item 51: 메서드 시그니처를 신중히 설계하라
- [ ] Item 52: 다중정의는 신중히 사용하라
- [ ] Item 53: 가변인수는 신중히 사용하라
- [ ] Item 54: null이 아닌 빈 컬렉션이나 배열을 반환하라
- [ ] Item 55: 옵셔널 반환은 신중히 하라
- [ ] Item 56: 공개된 API 요소에는 항상 문서화 주석을 작성하라

<br/>

## Chapter 9. 일반적인 프로그래밍 원칙

- [ ] Item 57: 지역 변수의 범위를 최소화하라
- [ ] Item 58: 전통적인 for 문보다는 for-each 문을 사용하라
- [ ] Item 59: 라이브러리를 익히고 사용하라
- [ ] Item 60: 정확한 답이 필요하다면 float와 double은 피하라
- [ ] Item 61: 박싱된 기본 타입보다는 기본 타입을 사용하라
- [ ] Item 62: 다른 타입이 적절하다면 문자열 사용을 피하라
- [ ] Item 63: 문자열 연결은 느리니 주의하라
- [ ] Item 64: 객체는 인터페이스를 사용해 참조하라
- [ ] Item 65: 리플렉션보다는 인터페이스를 사용하라
- [ ] Item 66: 네이티브 메서드는 신중히 사용하라
- [ ] Item 67: 최적화는 신중히 하라
- [ ] Item 68: 일반적으로 통용되는 명명 규칙을 따르라

<br/>

## Chapter 10. 예외

<br/>

## Chapter 11. 동시성

<br/>

## Chapter 12. 직렬화
