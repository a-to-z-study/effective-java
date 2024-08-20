package me.java;

import static me.java.chapter2.NewYorkPizza.Size.*;
import static me.java.chapter2.Pizza.Topping.*;

import me.java.chapter2.CalzonePizza;
import me.java.chapter2.NewYorkPizza;
import me.java.chapter2.NutritionFacts;
import me.java.chapter2.Room;

public class Main {
	public static void main(String[] args) {

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
	}
}
