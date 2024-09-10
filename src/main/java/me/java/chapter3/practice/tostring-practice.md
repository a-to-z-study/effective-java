# Table of Contents

- [`toString()`을 재정의하여 로깅에 활용하기](#tostring을-재정의하여-로깅에-활용하기)

<br/>

# `toString()`을 재정의하여 로깅에 활용하기

기존 User 클래스의 `toString()`은 Object 클래스에서 상속받은 `toString()`을 사용하기 때문에 `User@1a2b3c4d`와 같이 의미없는 정보를 반환한다.  
따라서 아래와 같이 getter 메서드를 활용하여 직접 필요한 데이터를 가져와서 로그를 남겼다.
```java
log.info("Session Creation Success: id={}, nickname={}, currentRoomCode={}, activate={}",
        user.getId(), user.getNickname(), user.getCurrentRoomCode(), user.isActivate());
```

단, getter 메서드에 예외 처리나 검증 로직이 포함된 경우 예상치 못한 예외가 발생할 수 있으므로 주의가 필요하다.

이때 여러 개의 getter 메서드를 조합하는 번거로운 과정을 `toString()`을 재정의하여 단순화할 수 있다.
```java
@Override
public String toString() {
    return String.format("[User: id=%s, nickname=%s, currentRoomCode=%s, activate=%s]", id, nickname, currentRoomCode, activate);
}
```

새로운 사용자 세션 생성에 성공한 경우 다음과 같은 로그를 남기도록 구현하였다.
```shell
... http-nio-8080-exec-1 [INFO ] [SessionCreateInterceptor:72] Session Creation Success: [User: id=1, nickname=CrimsonDragon308, currentRoomCode=null, activate=true]
```

새로운 방 생성에 성공했을 때에도 다음과 같은 로그를 남기도록 했다.
```shell
... http-nio-8080-exec-3 [INFO ] [RoomService:67] Room Creation Success: [Room: code=54bb4f336a, capacity=10, password=false, createdAt=2024-07-22T18:19:38.914226, activate=true]
```

ex) 사용자의 세션 생성 → 방 생성 → 방 입장하여 채팅에 참여 하는 과정이 담긴 로그
```shell
... http-nio-8080-exec-1 [INFO ] [SessionCreateInterceptor:72] Session Creation Success: [User: id=1, nickname=LunarJester474, currentRoomCode=null, activate=true]
... http-nio-8080-exec-2 [INFO ] [RoomService:67] Room Creation Success: [Room: code=6404b9d1ad, capacity=3, password=false, createdAt=2024-08-22T18:43:56.603331, activate=true]
... http-nio-8080-exec-6 [INFO ] [StompHandshakeInterceptor:45] User 1 starts a websocket connection
```
