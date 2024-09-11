package me.java.chapter6;

import static me.java.chapter6.StrategicPayrollDay.PayType.*;

public enum StrategicPayrollDay {

	MONDAY(WEEKDAY),
	TUESDAY(WEEKDAY),
	WEDNESDAY(WEEKDAY),
	THURSDAY(WEEKDAY),
	FRIDAY(WEEKDAY),
	SATURDAY(WEEKEND),
	SUNDAY(WEEKEND);

	private final PayType payType;

	StrategicPayrollDay(PayType payType) {
		this.payType = payType;
	}

	// 잔업 수당 계산을 전략 열거 타입에 위임하여 switch 문이나 상수별 메서드 구현이 필요 없게 된다
	// 이 패턴은 switch 문보다 복잡하지만 더 안전하고 유연하다
	// 단, 기존 열거 타입에 상수별 동작을 혼합해 넣을 때는 switch 문이 좋은 선택이 될 수 있다
	// 열거 타입은 필요한 원소를 컴파일타임에 다 알 수 있는 상수 집합이라면 사용하자
	// 열거 타입에 정의된 상수 개수가 영원히 고정 불변일 필요는 없다
	enum PayType {
		WEEKDAY {
			int overtimePay(int minsWorked, int payRate) {
				return minsWorked <= MINS_PER_SHIFT ? 0 :
					(minsWorked - MINS_PER_SHIFT) * payRate / 2;
			}
		},
		WEEKEND {
			int overtimePay(int minsWorked, int payRate) {
				return minsWorked * payRate / 2;
			}
		};

		abstract int overtimePay(int mins, int payRate);

		private static final int MINS_PER_SHIFT = 8 * 60;

		int pay(int minsWorked, int payRate) {
			int basePay = minsWorked * payRate;
			return basePay + overtimePay(minsWorked, payRate);
		}
	}


}
