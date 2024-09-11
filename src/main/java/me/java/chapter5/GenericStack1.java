package me.java.chapter5;

import java.util.Arrays;
import java.util.EmptyStackException;

public class GenericStack1<E> {

	private E[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	@SuppressWarnings("unchecked")
	public GenericStack1() {
		// E와 같은 실체화 불가 타입으로는 배열을 만들 수 없다
		// 첫 번째 해결 방법: 금지하는 제약을 대놓고 우회하기
		// 비검사 형변환이 안전한지 스스로 확인했다면 @SuppressWarnings 애너테이션으로 범위를 최소로 좁혀 경고를 숨긴다
		elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(E e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public E pop() {
		if (size == 0)
			throw new EmptyStackException();

		E result = elements[--size];
		elements[size] = null; // 다 쓴 참조 해제

		return result;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void ensureCapacity() {
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}

}
