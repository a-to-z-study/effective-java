package me.java.chapter6;

public enum BranchingEnumTypes {

	PLUS, MINUS, TIMES, DIVIDE;

	// 상수가 뜻하는 연산을 수행한다
	public double apply(double x, double y) {
		switch (this) {
			case PLUS: return x + y;
			case MINUS: return x - y;
			case TIMES: return x * y;
			case DIVIDE: return x / y;
		}

		throw new AssertionError("알 수 없는 연산: " + this);
	}

	// 새로운 상수를 추가하면 해당 CASE 문도 추가해야 한다
	// 혹시라도 깜빡한다면 컴파일은 되지만 새로 추가한 연산을 수행하려 할 때
	// "알 수 없는 연산"이라는 런타임 오류를 내며 프로그램이 종료된다
}
