package de.croggle.util.convert;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import de.croggle.game.Color;
import de.croggle.game.board.AgedAlligator;
import de.croggle.game.board.Board;
import de.croggle.game.board.BoardObject;
import de.croggle.game.board.ColoredAlligator;
import de.croggle.game.board.Egg;
import de.croggle.game.board.InternalBoardObject;
import junit.framework.TestCase;

public class JsonToAlligatorTest extends TestCase {

	public void testManualSimple() {
		String json = "{ " + "\"families\" : [" + "{" + "\"type\" : \"egg\","
				+ "\"movable\" : true," + "\"removable\" : true,"
				+ "\"color\" : 0," + "\"recolorable\" : true" + "}" + "]}";
		BoardObject bo = JsonToAlligator.convert(json);
		assertTrue(bo.getClass() == Board.class);
		Board b = (Board) bo;
		assertEquals(1, b.getChildCount());
		InternalBoardObject ibo = b.getFirstChild();
		assertTrue(ibo.getClass() == Egg.class);
		Egg e = (Egg) ibo;
		assertTrue(e.isMovable());
		assertTrue(e.isRecolorable());
		assertTrue(e.isRemovable());
		assertEquals(0, e.getColor().getId());
	}
	
	public void testManualSimpleTwo() {
		String start = "{\n\t\"" + "families\" : [\n" + "\t\t{\n"
				+ "\t\t\t\"type\" : \"colored alligator\",\n"
				+ "\t\t\t\"color\" : 0,\n" + "\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\"recolorable\" : true,\n"
				+ "\t\t\t\"children\" : [\n" + "\t\t\t\t{\n"
				+ "\t\t\t\t\t\"type\" : \"egg\",\n"
				+ "\t\t\t\t\t\"color\" : 0,\n"
				+ "\t\t\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\t\t\"recolorable\" : true\n" + "\t\t\t\t}\n"
				+ "\t\t\t]\n" + "\t\t},\n" + "\t\t{\n"
				+ "\t\t\t\"type\" : \"egg\",\n" + "\t\t\t\"color\" : 1,\n"
				+ "\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\"recolorable\" : true\n"
				+ "\t\t},\n" + "\t\t{\n"
				+ "\t\t\t\"type\" : \"egg\",\n" + "\t\t\t\"color\" : 1,\n"
				+ "\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\"recolorable\" : true\n"
				+ "\t\t}\n\t]\n" + "}\n";		
		
		Board board = JsonToAlligator.convertBoard(start);
		
		Board b = new Board();
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		b.addChild(e2);
		Egg e3 = new Egg(true, true, new Color(1), true);
		b.addChild(e3);
		
		
		assertTrue(board.match(b));
		
	}
	
	public void testInternalConvert() {
		Egg e1 = new Egg(true, true, new Color(0), true);
		ColoredAlligator c2 = new ColoredAlligator(true, true, new Color(0), true);
		c2.addChild(e1);
		String testString = AlligatorToJson.convert(e1);
		JsonValue testJson = new JsonValue(testString);
		assertTrue(e1.match(JsonToAlligator.convertInternalBoardObject(testString)));
	}
	
	public void testMoreComplexManual() {
		Board b = new Board();
		ColoredAlligator a = new ColoredAlligator(true, true, new Color(0),
				true);
		b.addChild(a);
		Egg e1 = new Egg(true, true, new Color(0), true);
		a.addChild(e1);
		Egg e2 = new Egg(true, true, new Color(1), true);
		a.addChild(e2);
		Egg e3 = new Egg(true, true, new Color(1), true);
		b.addChild(e3);
		AgedAlligator a2 = new AgedAlligator(true, true);
		b.addChild(a2);
		ColoredAlligator a3 = new ColoredAlligator(true, true, new Color(2),
				true);
		ColoredAlligator a4 = new ColoredAlligator(true, true, new Color(4),
				true);
		a2.addChild(a3);
		a2.addChild(a4);
		Egg e4 = new Egg(true, true, new Color(2), true);
		Egg e5 = new Egg(true, true, new Color(6), true);
		Egg e6 = new Egg(true, true, new Color(4), true);
		a3.addChild(e4);
		a3.addChild(e5);
		a4.addChild(e6);
		ColoredAlligator a5 = new ColoredAlligator(true, true, new Color(4),
				true);
		a4.addChild(a5);
		AgedAlligator a6 = new AgedAlligator(true, true);
		b.addChild(a6);
		
		String testString = AlligatorToJson.convert(b);
		BoardObject bTest = JsonToAlligator.convert(testString);
		
		assertTrue(b.match(bTest));
	}
	
	public void testEmptyBoard() {
		Board b = new Board();
		
		String testString = AlligatorToJson.convert(b);
		BoardObject bTest = JsonToAlligator.convert(testString);
		assertTrue(b.match(bTest));
	}
	
	public void testExceptions() {
		String start = "{\n\t\"" + "families_fake\" : [\n" + "\t\t{\n"  // The name "family" was changed to provoke the exception.
				+ "\t\t\t\"type\" : \"colored alligator\",\n"
				+ "\t\t\t\"color\" : 0,\n" + "\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\"recolorable\" : true,\n"
				+ "\t\t\t\"children\" : [\n" + "\t\t\t\t{\n"
				+ "\t\t\t\t\t\"type\" : \"egg\",\n"
				+ "\t\t\t\t\t\"color\" : 0,\n"
				+ "\t\t\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\t\t\"recolorable\" : true\n" + "\t\t\t\t}\n"
				+ "\t\t\t]\n" + "\t\t},\n" + "\t\t{\n"
				+ "\t\t\t\"type\" : \"egg\",\n" + "\t\t\t\"color\" : 1,\n"
				+ "\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\"recolorable\" : true\n"
				+ "\t\t},\n" + "\t\t{\n"
				+ "\t\t\t\"type\" : \"egg\",\n" + "\t\t\t\"color\" : 1,\n"
				+ "\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\"recolorable\" : true\n"
				+ "\t\t}\n\t]\n" + "}\n";
		
		String fakeChildren = "{\n\t\"" + "families\" : [\n" + "\t\t{\n"
				+ "\t\t\t\"type\" : \"colored alligator_fake\",\n"
				+ "\t\t\t\"color\" : 0,\n" + "\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\"recolorable\" : true,\n"
				+ "\t\t\t\"children\" : [\n" + "\t\t\t\t{\n"
				+ "\t\t\t\t\t\"type\" : \"egg\",\n"
				+ "\t\t\t\t\t\"color\" : 0,\n"
				+ "\t\t\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\t\t\"recolorable\" : true\n" + "\t\t\t\t}\n"
				+ "\t\t\t]\n" + "\t\t},\n" + "\t\t{\n"
				+ "\t\t\t\"type\" : \"egg\",\n" + "\t\t\t\"color\" : 1,\n"
				+ "\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\"recolorable\" : true\n"
				+ "\t\t},\n" + "\t\t{\n"
				+ "\t\t\t\"type\" : \"egg\",\n" + "\t\t\t\"color\" : 1,\n"
				+ "\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\"recolorable\" : true\n"
				+ "\t\t}\n\t]\n" + "}\n";
		
		String fakeArray = "{\n\t\"" + "families\" : [\n" + "\t\t{\n"
				+ "\t\t\t\"type\" : \"colored alligator_fake\",\n"
				+ "\t\t\t\"color\" : 0,\n" + "\t\t\t\"movable\" : true,\n"
				+ "\t\t\t\"removable\" : true,\n"
				+ "\t\t\t\"recolorable\" : true,\n"
				+ "\t\t\t\"children\" : [\n" + "\t\t\t\t{\n"
				+ "\t\t\t\t}\n"
				+ "\t\t\t]\n" + "\t\t},\n" + "\t\t{\n"
				+ "\t\t},\n" + "\t\t{\n"
				+ "\t\t}\n\t]\n" + "}\n";
		
		JsonReader reader = new JsonReader();
		JsonValue board = null;
		
		try {
			
			JsonToAlligator.convertBoard(board);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		
		
		board = reader.parse(start);
		
		try {
			JsonToAlligator.convertBoard(board);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertTrue(true);
		}
		
		board = reader.parse(fakeChildren);
		
		try {
			JsonToAlligator.convertInternalBoardObject(fakeChildren);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			JsonToAlligator.convert(fakeArray);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertTrue(true);
		}
	}
}
