package me.java.chapter6;

public class Sample {

	@Test
	public static void method1() { // 성공해야 한다

	}

	public static void method2() { // 수행되지 않는다

	}

	@Test
	public static void method3() { // 실패해야 한다
		throw new RuntimeException("런타임 예외");
	}

	@Test
	public void method5() {  // 잘못 사용한 예: 정적 메서드가 아니다

	}

}
