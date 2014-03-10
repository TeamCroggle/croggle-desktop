package de.croggle.util;

import junit.framework.TestCase;

public class MapEntryTest extends TestCase {

	public void testHash() {
		MapEntry mapEntry = new MapEntry();
		mapEntry.setKey(1);
		mapEntry.setValue(2);
		assertNotNull(mapEntry.hashCode());
		mapEntry.setKey(null);
		mapEntry.setValue(null);
		assertNotNull(mapEntry.hashCode());
	}
	
	public void testEquals() {
		MapEntry mapEntry1 = new MapEntry<Integer, Integer>(1, 1);
		MapEntry mapEntry1Eq = new MapEntry<Integer, Integer>(1, 1);
		MapEntry mapEntry2 = new MapEntry<Integer, Integer>(3, 3);
		MapEntry mapEntry3 = null;
		MapEntry mapEntry4 = new MapEntry<Integer, Integer>(null, null);
		MapEntry mapEntry5 = new MapEntry<Integer, Integer>(1, null);
		MapEntry mapEntry5Eq = new MapEntry<Integer, Integer>(1, null);
		MapEntry mapEntry6 = new MapEntry<Integer, Integer>(null, 1);
		int fakeEntry = 1;
		
		mapEntry1.getKey();
		mapEntry1.getValue();
		
		
		assertTrue(mapEntry1.equals(mapEntry1));
		assertFalse(mapEntry1.equals(mapEntry3));
		assertFalse(mapEntry1.equals(mapEntry2));
		assertFalse(mapEntry1.equals(fakeEntry));
		assertFalse(mapEntry4.equals(mapEntry1));
		assertTrue(mapEntry4.equals(mapEntry4));
		assertFalse(mapEntry1.equals(mapEntry6));
		assertFalse(mapEntry1.equals(mapEntry5));
		assertFalse(mapEntry5.equals(mapEntry6));
		assertFalse(mapEntry6.equals(mapEntry5));
		assertFalse(mapEntry5.equals(mapEntry2));
		assertTrue(mapEntry5.equals(mapEntry5));
		assertFalse(mapEntry4.equals(mapEntry5));
		assertFalse(mapEntry4.equals(mapEntry6));
		assertFalse(mapEntry2.equals(mapEntry5));
		assertFalse(mapEntry5.equals(mapEntry4));
		assertFalse(mapEntry6.equals(mapEntry4));
		assertTrue(mapEntry1.equals(mapEntry1Eq));
		assertFalse(mapEntry2.equals(mapEntry5));
		assertFalse(mapEntry2.equals(mapEntry6));
		assertFalse(mapEntry5.equals(mapEntry2));
		assertFalse(mapEntry6.equals(mapEntry2));
		assertFalse(mapEntry5.equals(mapEntry1));
		assertFalse(mapEntry5.equals(mapEntry2));
		assertFalse(mapEntry6.equals(mapEntry2));
		assertFalse(mapEntry6.equals(mapEntry1));
		assertTrue(mapEntry5.equals(mapEntry5Eq));
		
		
	}
}
