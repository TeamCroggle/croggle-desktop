package de.croggle.game.level;

import junit.framework.TestCase;

public class InvalidJsonExceptionTest extends TestCase {

	public void testException(){
		try {
			throw new InvalidJsonException();
		} catch (InvalidJsonException e) {
			
		}
	}

	
	public void testExceptionMessage(){
		try {
			throw new InvalidJsonException("test");
		} catch (InvalidJsonException e) {
			
		}
	}

}
