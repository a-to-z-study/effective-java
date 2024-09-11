package me.java.chapter6;

public enum UsingMethodEnumTypes {

	PLUS("+") {
		public double apply(double x, double y) {
			return x + y;
		}
	},
	MINUS("-") {
		public double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES("*") {
		public double apply(double x, double y) {
			return x * y;
		}
	},
	DIVIDE("/") {
		public double apply(double x, double y) {
			return x / y;
		}
	};

	private final String symbol;

	UsingMethodEnumTypes(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}

	public abstract double apply(double x, double y);
	// apply 메서드가 상수 선언 바로 옆에 붙어 있으니 새로운 상수를 추가할 때
	// apply도 재정의해야 한다는 사실을 깜빡하기는 어려울 것이다
	// 뿐만 아니라 apply가 추상 메서드이므로 재정의하지 않았다면 컴파일 오류로 알려준다
}
