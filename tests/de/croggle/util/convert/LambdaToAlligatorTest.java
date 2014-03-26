package de.croggle.util.convert;

import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.board.Board;
import de.croggle.game.board.Egg;

/**
 * 
 */
public class LambdaToAlligatorTest extends TestCase {

	public LambdaToAlligatorTest() {
	}

	@Override
	protected void setUp() throws Exception {

	}

	public void testSimpleTerm() {
		Board b = LambdaToAlligator.convert("(λx.x) y");
		String s = AlligatorToLambda.convert(b);
		assertEquals("(λx.x) y", s);
	}

	public void testComplexTerm() {
		String term = "(λa.λb.λs.λz.(a s (b s z))) (λs.λz.(s (s (s z)))) (λs.λz.(s (s (s (s z)))))";
		String expected = "(λx.λy.λz.λp.(x z (y z p))) (λz.λp.(z (z (z p)))) λz.λp.(z (z (z (z p))))";
		Board b = LambdaToAlligator.convert(term);
		String s = AlligatorToLambda.convert(b);
		assertEquals(expected, s);
	}

	public void testLongVariableNames() {
		String term = "λone.one two";
		Board b = LambdaToAlligator.convert(term);
		String s = AlligatorToLambda.convert(b);
		assertEquals("λx.x y", s);
	}
	
	public void testExceptions() {
		String term1 = "λx.(x.y)";
		String term2 = "(λx.x y";
		String term3 = "(λx x) y";
		String term4 = "λ.x";
		String term5 = "(λx.x) y q w e r t z u i o p a s d f g h j k l c v b n m 1 2 3 4 5 6 7 8 9";
		try {
			LambdaToAlligator.convert(term5);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			LambdaToAlligator.convert(term2);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		
		try {
			LambdaToAlligator.convert(term1);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			LambdaToAlligator.convert(term3);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			LambdaToAlligator.convert(term4);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		
		
	}

	@Override
	public void tearDown() {
	}

}
