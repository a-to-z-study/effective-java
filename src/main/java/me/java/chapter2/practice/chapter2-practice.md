# Lombok의 `@Builder`와 직접 구현한 빌더 패턴 비교

## Table of Contents
- [Lombok의 `@Builder`를 사용했을 때의 장단점](#1-lombok의-builder를-사용한-경우)
- [직접 구현한 빌더 패턴을 사용했을 때의 장단점](#2-직접-구현한-빌더-패턴을-사용한-경우)

<br/>

## 1. Lombok의 `@Builder`를 사용한 경우

일반적으로 전달 가능한 파라미터를 포함하는 생성자에 `@Builder`를 붙이는 것만으로도 Lombok에서 제공하는 빌더 패턴을 사용할 수 있다.
```java
@Builder
private User(Long id, String nickname, String currentRoomCode, boolean activate) {
    this.id = id;
    this.nickname = nickname;
    this.currentRoomCode = currentRoomCode;
    this.activate = activate;
}
```

클래스를 디컴파일하면 다음과 같은 코드가 자동 생성된 것을 확인할 수 있다.
```java
public static UserBuilder builder() {
    return new UserBuilder();
}

public static class UserBuilder {
    private Long id;
    private String nickname;
    private String currentRoomCode;
    private boolean activate;
    
    UserBuilder() {
    }
    
    public UserBuilder id(final Long id) {
        this.id = id;
        return this;
    }
    
    public UserBuilder nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }
    
    public UserBuilder currentRoomCode(final String currentRoomCode) {
        this.currentRoomCode = currentRoomCode;
        return this;
    }
    
    public UserBuilder activate(final boolean activate) {
        this.activate = activate;
        return this;
    }
    
    public User build() {
        return new User(this.id, this.nickname, this.currentRoomCode, this.activate);
    }
    
    public String toString() {
        return "User.UserBuilder(id=" + this.id + ", nickname=" + this.nickname + ", currentRoomCode=" + this.currentRoomCode + ", activate=" + this.activate + ")";
    }
}
```

그러나 `id`와 `nickname`이 필수 매개변수라고 가정할 때, 이를 포함하지 않아도 다음과 같이 `build()`할 수 있으므로 개발자의 주의가 필요하다.
```java
User.builder().build();
```

이때 필수 매개변수를 전달하지 않았을 때 예외를 발생시키는 전략을 취할 수도 있다.
```java
@Builder
private User(Long id, String nickname, String currentRoomCode, boolean activate) {
    if (id == null || nickname == null) {
        throw new IllegalArgumentException("id and nickname are required");
    }
	
    this.id = id;
    this.nickname = nickname;
    this.currentRoomCode = currentRoomCode;
    this.activate = activate;
}
```
이 전략은 런타임 시점에서 오류를 감지하기 때문에 컴파일 시점에서는 알 수 없다는 단점이 존재한다.

> 정리  
> 생성자에 `@Builder`만 적용하면 간편하게 빌더 패턴을 사용할 수 있다는 장점이 있지만,
> 컴파일 시점에 필수 매개변수를 검증하기 어렵다는 단점이 있다.

<br/>

## 2. 직접 구현한 빌더 패턴을 사용한 경우

다음과 같이 필수 매개변수와 선택 매개변수를 구분하여 빌더를 구현할 수 있다.  
이때 선택 매개변수의 기본값을 원하는 값으로 초기화할 수 있다.
```java
public static class Builder {
    // 필수 매개변수
    private final Long id;  
    private final String nickname;

    // 선택 매개변수
    private String currentRoomCode = null;
    private boolean activate = false;

    public Builder(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public Builder currentRoomCode(String val) {
        currentRoomCode = val;
        return this;
    }

    public Builder activate(boolean val) {
        activate = val;
        return this;
    }

    public User build() {
        return new User(this);
    }
}

private User(Builder builder) {
    id = builder.id;
    nickname = builder.nickname;
    currentRoomCode = builder.currentRoomCode;
    activate = builder.activate;
}
```

빌더 사용 시 아래와 같이 필수 매개변수 전달을 강제하기 때문에 오류를 예방할 수 있다.
```java
User user = new User
    .Builder(id, nickname)
    .activate(true)
    .build();
```

> 정리  
> 컴파일 시점에 필요한 검증을 수행할 수 있다는 장점이 있지만, 개발자가 직접 빌더를 구현해야 하고, 성능에 민감한 상황에서는 빌더 생성 비용이 문제가 될 수 있다는 단점이 있다.
