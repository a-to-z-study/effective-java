package me.java;

import static me.java.chapter2.NewYorkPizza.Size.*;
import static me.java.chapter2.Pizza.Topping.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.java.chapter2.CalzonePizza;
import me.java.chapter2.NewYorkPizza;
import me.java.chapter2.NutritionFacts;
import me.java.chapter2.Room;
import me.java.chapter6.Test;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException {

		// 빌더 패턴 사용 예시 (chapter 2)
		NutritionFacts cocaCola = new NutritionFacts
			.Builder(240, 8)
			.calories(100)
			.sodium(35)
			.carbohydrate(27)
			.build();

		NewYorkPizza newYorkPizza = new NewYorkPizza
			.Builder(SMALL)
			.addTopping(SAUSAGE)
			.addTopping(ONION)
			.build();

		CalzonePizza calzonePizza = new CalzonePizza
			.Builder()
			.addTopping(HAM)
			.sauceInside()
			.build();

		// cleaner 동작 테스트 (chapter 2)
		// test 1
		try (Room myRoom = new Room(7)) {
			System.out.println("안녕?");
		}
		// 결과: '안녕?' 출력 후 '방 청소 끝~' 출력

		// test 2
		new Room(99);
		System.out.println("잘 하겠지?");
		// 결과: '잘 하겠지?'만 출력

		// 마커 애너테이션을 처리하는 프로그램 (chapter 6)
		// Program arguments에 me.java.chapter6.Sample을 추가한다
		// 결과
		// public static void me.java.chapter6.Sample.method3() 실패: java.lang.RuntimeException: 런타임 예외
		// 잘못 사용한 @Test: public void me.java.chapter6.Sample.method5()
		// 성공: 1, 실패: 2
		int tests = 0;
		int passed = 0;

		Class<?> testClass = Class.forName(args[0]);
		for (Method method : testClass.getDeclaredMethods()) {
			if (method.isAnnotationPresent(Test.class)) {
				tests++;
				try {
					// null을 넘기는 것은 정적 메서드에 대한 호출로 해석된다
					// 인스턴스 메서드라면 첫 번째 인자로 해당 메서드를 소유하는 객체의 인스턴스를 넘겨야 한다
					method.invoke(null);
					passed++;
				} catch (InvocationTargetException wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					System.out.println(method + " 실패: " + exc);
				} catch (Exception exc) {
					System.out.println("잘못 사용한 @Test: " + method);
				}
			}
		}
		System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
	}
}
