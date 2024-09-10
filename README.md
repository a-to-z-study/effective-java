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
    - - [practice 1: `toString()`을 재정의하여 로깅에 활용하기](./src/main/java/me/java/chapter3/practice/tostring-practice.md)
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

<br/>

## Chapter 6. 열거 타입과 애너테이션

<br/>

## Chapter 7. 람다와 스트림

<br/>

## Chapter 8. 메서드

<br/>

## Chapter 9. 일반적인 프로그래밍 원칙

<br/>

## Chapter 10. 예외

<br/>

## Chapter 11. 동시성

<br/>

## Chapter 12. 직렬화
