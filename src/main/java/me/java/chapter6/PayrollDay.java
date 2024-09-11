package me.java.chapter6;

public enum PayrollDay {

	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

	private static final int MINS_PER_SHIFT = 8 * 60;

	int pay(int minutesWorked, int payRate) {
		int basePay = minutesWorked * payRate;

		int overtimePay;
		switch (this) {
			case SATURDAY: case SUNDAY: // 주말
				overtimePay = basePay / 2;
				break;
			default: // 주중
				overtimePay = minutesWorked <= MINS_PER_SHIFT ?
					0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
		}

		return basePay + overtimePay;
	}

	// 간결하지만 관리 관점에서는 위험한 코드다
	// 휴가와 같은 새로운 값을 열거 타입에 추가하려면 그 값을 처리하는 case 문을 잊지 말고 쌍으로 넣어줘야 한다
	// 자칫 깜빡하는 날에는 휴가 기간에 열심히 일해도 평일과 똑같은 임금을 받게 된다
}
