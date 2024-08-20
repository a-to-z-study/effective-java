package me.java.chapter2;

public enum EnumElvis {

	/*
	대부분의 상황에서 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법이다.
	단, 만들려는 싱글턴이 Enum 외의 클래스를 상속해야 한다면 이 방법은 사용할 수 없다.
	 */
	INSTANCE;

	public void leaveTheBuilding() {}

}
