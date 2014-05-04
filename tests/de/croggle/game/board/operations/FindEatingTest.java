package de.croggle.game.board.operations;

import junit.framework.TestCase;
import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.util.convert.LambdaToAlligator;

public class FindEatingTest extends TestCase {

	public void testSimple() {
		Board b = new Board();
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);

		assertEquals(a, FindEating.findEater(b));
	}

	/**
	 * test if the left-most eater eats, not the top-most
	 */
	public void testPrecedence() {
		// the following term can be roughly given as ((λx.x) (λy.y)) (λx.x) y
		Board b = new Board();
		AgedAlligator aa = new AgedAlligator(true, true);
		b.addChild(aa);
		ColoredAlligator a1 = new ColoredAlligator(true, true, new Color(0),
				true);
		aa.addChild(a1);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a1.addChild(e1);

		ColoredAlligator a2 = new ColoredAlligator(true, true, new Color(0),
				true);
		aa.addChild(a2);
		Egg e2 = new Egg(true, true, new Color(0), true);
		a2.addChild(e2);

		ColoredAlligator a3 = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a3);
		Egg e3 = new Egg(true, true, new Color(0), true);
		a3.addChild(e3);

		Egg e4 = new Egg(true, true, new Color(1), true);
		b.addChild(e4);

		assertEquals(a1, FindEating.findEater(b));
	}

	/**
	 * Test if the associativity is being complied with. That means that the
	 * operation must not find eatings on alligators which are preceded by eggs.
	 */
	public void testAssociativity() {
		Board b = LambdaToAlligator.convert("x λx.x y");
		ColoredAlligator eater = FindEating.findEater(b);
		assertNull(eater);
	}
}
