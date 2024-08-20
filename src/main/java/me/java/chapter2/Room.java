package me.java.chapter2;

import java.lang.ref.Cleaner;

public class Room implements AutoCloseable {

	private static final Cleaner cleaner = Cleaner.create();

	// 청소가 필요한 자원으로 절대 Room을 참조해서는 안 된다!
	private static class State implements Runnable {
		int numJunkPiles; // 방 안의 쓰레기 수, 더 현실적으로 만들려면 이 필드는 네이티브 피어를 가리키는 포인터를 담은 final long 변수여야 한다.

		State(int numJunkPiles) {
			this.numJunkPiles = numJunkPiles;
		}

		@Override // close 메서드나 cleaner가 호출한다.
		public void run() {
			numJunkPiles = 0;
			System.out.println("방 청소 끝~");
		}
	}

	private final State state; // cleanable과 공유하는 방의 상태
	private final Cleaner.Cleanable cleanable; // 수거 대상이 되면 방을 청소한다.

	public Room(int numJunkPiles) {
		state = new State(numJunkPiles);
		cleanable = cleaner.register(this, state);
	}

	@Override
	public void close() {
		cleanable.clean();
	}

}
