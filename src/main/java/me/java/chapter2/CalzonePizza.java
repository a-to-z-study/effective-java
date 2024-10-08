package me.java.chapter2;

public class CalzonePizza extends Pizza {

	private final boolean sauceInside;

	public static class Builder extends Pizza.Builder<Builder> {
		private boolean sauceInside = false; // 기본값

		public Builder sauceInside() {
			sauceInside = true;
			return this;
		}

		@Override
		public CalzonePizza build() {
			return new CalzonePizza(this);
		}

		@Override
		protected Builder self() {
			return this;
		}
	}

	private CalzonePizza(Builder builder) {
		super(builder);
		sauceInside = builder.sauceInside;
	}

}
