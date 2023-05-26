import static org.junit.Assert.*;
import java.util.List;
import java.util.Random;

import org.junit.*;

public class BSTTest {

	BST<String, Integer> generateBST(int n) {
		BST<String, Integer> tree = new BST<String, Integer>();
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			tree.set(Character.toString((char)(rand.nextInt(26) + 65)), rand.nextInt(1000));
		}
		return tree;
	}
	
	/* TODO: Add your own tests */
	@Test
	public void dummyTest() {
		
	}

	@Test 
	public void testPutDuplicate() {
		BST<String, Integer> tree = new BST<String, Integer>();
		tree.set("A", 1);
		tree.set("A", 2);
		assertEquals(1, tree.size());
	}

	@Test 
	public void testPutNullKey() {
		BST<String, Integer> tree = new BST<String, Integer>();
		try {
			tree.set(null, 1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(BST.ILLEGAL_ARG_NULL_KEY, e.getMessage());
		}
	}

	@Test
	public void testReplace() {
		BST<String, Integer> tree = new BST<String, Integer>();
		tree.set("A", 1);
		tree.set("B", 2);
		tree.set("C", 3);
		tree.set("Z", 26);
		tree.set("A", 27);
		assertEquals(4, tree.size());
		assertEquals(27, (int)tree.get("A"));
	}

	@Test
	public void testReplaceNonExistentKey() {
		BST<String, Integer> tree = new BST<String, Integer>();
		tree.set("A", 1);
		tree.set("B", 2);
		tree.set("C", 3);
		tree.set("Z", 26);
		boolean replaced = tree.replace("D", 4);
		assertFalse(replaced);
		assertEquals(4, tree.size());
	}

	@Test
	public void testRemoveNullValue() {
		BST<String, Integer> tree = new BST<String, Integer>();
		for (int i = 0; i < 10; i++) {
			tree.set(Character.toString((char)(i + 65)), i);
		}
		tree.set("Z", null);
		tree.remove("Z");
		assertEquals(10, tree.size());
	}

	@Test
	public void testRemoveOnlyRoot() {
		BST<String, Integer> tree = new BST<String, Integer>();
		tree.set("A", 1);
		tree.remove("A");
		assertEquals(0, tree.size());
	}

	@Test
	public void testRemoveRootWithOneChild() {
		BST<String, Integer> tree = new BST<String, Integer>();
		tree.set("A", 1);
		tree.set("B", 2);
		tree.remove("A");
		assertEquals(1, tree.size());
	}

	@Test
	public void testSetReplaceOldKey() {
		BST<String, Integer> tree = new BST<String, Integer>();
		tree.set("A", 1);
		tree.set("B", 2);
		tree.set("C", 3);
		tree.set("Z", 26);
		tree.set("A", 27);
		assertEquals(4, tree.size());
		assertEquals(27, (int)tree.get("A"));
		assertEquals(2, (int)tree.get("B"));
	}

	@Test
	public void testSetNewKey() {
		BST<String, Integer> tree = new BST<String, Integer>();
		tree.set("A", 1);
		tree.set("B", 2);
		tree.set("C", 3);
		tree.set("Z", 26);
		tree.set("D", 4);
		assertEquals(5, tree.size());
		assertEquals(4, (int)tree.get("D"));
	}

	@Test
	public void testSize() {
		BST<String, Integer> tree = new BST<String, Integer>();
		assertEquals(0, tree.size());
		tree.set("A", 1);
		assertEquals(1, tree.size());
		tree.set("B", 2);
		assertEquals(2, tree.size());
		tree.set("C", 3);
		assertEquals(3, tree.size());
		tree.set("Z", 26);
		assertEquals(4, tree.size());
		tree.set("D", 4);
		assertEquals(5, tree.size());
	}

	@Test
	public void testLargeSize() {
		BST<String, Integer> tree = new BST<String, Integer>();
		for (int i = 0; i < 1000; i++) {
			tree.set(Character.toString((char)(i + 65)), i);
		}
	}

	@Test
	public void testIsEmpty() {
		BST<String, Integer> tree = new BST<String, Integer>();
		assertTrue(tree.isEmpty());
		tree.set("A", 1);
		assertFalse(tree.isEmpty());
		tree.set("B", 2);
		assertFalse(tree.isEmpty());
		tree.set("C", 3);
		assertFalse(tree.isEmpty());
		tree.set("Z", 26);
		assertFalse(tree.isEmpty());
		tree.set("D", 4);
		assertFalse(tree.isEmpty());
	}

	@Test
	public void testIsEmptyAfterRemoval() {
		BST<String, Integer> tree = new BST<String, Integer>();
		tree.set("A", 1);
		tree.set("B", 2);
		tree.set("C", 3);
		tree.set("Z", 26);
		tree.set("D", 4);
		tree.remove("A");
		assertFalse(tree.isEmpty());
		tree.remove("B");
		assertFalse(tree.isEmpty());
		tree.remove("C");
		assertFalse(tree.isEmpty());
		tree.remove("Z");
		assertFalse(tree.isEmpty());
		tree.remove("D");
		assertTrue(tree.isEmpty());
	}

	@Test
	public void testContainsKey() {
		BST<String, Integer> tree = new BST<String, Integer>();
		tree.set("A", 1);
		assertTrue(tree.containsKey("A"));
		tree.set("B", 2);
		assertTrue(tree.containsKey("B"));
		tree.set("C", 3);
		assertTrue(tree.containsKey("C"));
		tree.set("Z", 26);
		assertTrue(tree.containsKey("Z"));
		tree.set("D", 4);
		assertTrue(tree.containsKey("D"));
	}

	@Test
	public void testDoesNotContainKey() {
		BST<String, Integer> tree = new BST<String, Integer>();
		tree.set("A", 1);
		assertFalse(tree.containsKey("B"));
		tree.set("B", 2);
		assertFalse(tree.containsKey("C"));
		tree.set("C", 3);
		assertFalse(tree.containsKey("Z"));
		tree.set("Z", 26);
		assertFalse(tree.containsKey("D"));
		tree.set("D", 4);
		assertFalse(tree.containsKey("E"));
	}

	@Test
	public void testKeys() {
		BST<String, Integer> tree = new BST<String, Integer>();
		tree.set("A", 1);
		tree.set("B", 2);
		tree.set("C", 3);
		tree.set("Z", 26);
		tree.set("D", 4);
		List<String> keys = tree.keys();
		assertEquals(5, keys.size());
		assertTrue(keys.contains("A"));
		assertTrue(keys.contains("B"));
		assertTrue(keys.contains("C"));
		assertTrue(keys.contains("Z"));
		assertTrue(keys.contains("D"));
	}

	@Test
	public void testNoKeys() {
		BST<String, Integer> tree = new BST<String, Integer>();
		List<String> keys = tree.keys();
		assertEquals(0, keys.size());
	}

}