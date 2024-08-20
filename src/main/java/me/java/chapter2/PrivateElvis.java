package me.java.chapter2;

public class PrivateElvis {

	/*
	private static final 필드를 이용한 정적 팩터리 방식의 싱글턴
	 */
	private static final PrivateElvis INSTANCE = new PrivateElvis();

	private PrivateElvis() {};

	public static PrivateElvis getInstance() {
		return INSTANCE;
	}

	public void leaveTheBuilding() {}

}
