package me.java.chapter2;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {

	public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }

	final Set<Topping> toppings;

	abstract static class Builder<T extends Builder<T>> { // Item 30 참고
		EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

		public T addTopping(Topping topping) {
			toppings.add(Objects.requireNonNull(topping));
			return self();
		}

		abstract Pizza build();

		// 하위 클래스는 이 메서드를 재정의하여 "this"를 반환하도록 해야 한다.
		protected abstract T self();
	}

	Pizza(Builder<?> builder) {
		toppings = builder.toppings.clone(); // Item 50 참고
	}

}
