package me.java.chapter2;

public class PublicElvis {

	/*
	public static final 필드 방식의 싱글턴
	 */
	public static final PublicElvis INSTANCE = new PublicElvis();

	private PublicElvis() {} // private 생성자는 public static final 필드인 PublicElvis.INSTANCE를 초기화할 때 딱 한 번만 호출된다.

	public void leaveTheBuilding() {}

	// 싱글턴임을 보장해주는 메서드
	private Object readResolve() { // Item 89 참고
		// 진짜 Elvis를 반환하고 가짜 Elvis는 가비지 컬렉터에 맡긴다.
		return INSTANCE;
	}

}
