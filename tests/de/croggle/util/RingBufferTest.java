package de.croggle.util;

import junit.framework.TestCase;

public class RingBufferTest extends TestCase {
	private final static int TEST_SIZE = 30;
	private RingBuffer<Object> ringBuffer;
	private PatternBuilder patternBuilder;

	protected void setUp() {
		ringBuffer = new RingBuffer<Object>(TEST_SIZE);
		patternBuilder = new PatternBuilder();
		
	}

	public void testPush() {
		ringBuffer.push(new Object());
	}

	public void testEmptyPop() {
		try {
			ringBuffer.pop();
			fail("Expected exception on empty buffer");
		} catch (Exception e) {
		}
	}

	public void testMinSize() throws Exception {
		final Object[] objects = new Object[TEST_SIZE];
		for (int i = 0; i < TEST_SIZE; i++) {
			final Object object = new Object();
			objects[i] = object;
			ringBuffer.push(object);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(objects[TEST_SIZE - i - 1], ringBuffer.pop());
		}
	}

	public void testMaxSize() throws Exception {
		final Object[] objects = new Object[TEST_SIZE];
		ringBuffer.push(new Object());
		for (int i = 0; i < TEST_SIZE; i++) {
			final Object object = new Object();
			objects[i] = object;
			ringBuffer.push(object);
		}
		for (int i = 0; i < TEST_SIZE; i++) {
			assertEquals(objects[TEST_SIZE - i - 1], ringBuffer.pop());
		}
		testEmptyPop();
	}

	public void testPushSize() {
		assertTrue(ringBuffer.size() == 0);
		Object object = new Object();
		ringBuffer.push(object);
		assertTrue(ringBuffer.size() == 1);
		ringBuffer.push(object);
		assertTrue(ringBuffer.size() == 2);
	}

	public void testIllegalSize() throws IllegalArgumentException {
		try {
			ringBuffer = new RingBuffer<Object>(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
}
