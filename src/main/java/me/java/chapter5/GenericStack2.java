package me.java.chapter5;

import java.util.Arrays;
import java.util.EmptyStackException;

public class GenericStack2<E> {

	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public GenericStack2() {
		// E와 같은 실체화 불가 타입으로는 배열을 만들 수 없다
		// 두 번째 해결 방법: elements 필드의 타입을 E[]에서 Object[]로 바꾸기
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(E e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public E pop() {
		if (size == 0)
			throw new EmptyStackException();

		// 비검사 형변환이 안전한지 스스로 확인했다면 @SuppressWarnings 애너테이션으로 범위를 최소로 좁혀 경고를 숨긴다
		@SuppressWarnings("unchecked")
		E result = (E)elements[--size];

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
