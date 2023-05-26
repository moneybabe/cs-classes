import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.*;

public class MyHashMapTest {
	
	private DefaultMap<String, String> testMap; // use this for basic tests
	private DefaultMap<String, String> mapWithCap; // use for testing proper rehashing
	public static final String TEST_KEY = "Test Key";
	public static final String TEST_VAL = "Test Value";
	
	@Before
	public void setUp() {
		testMap = new MyHashMap<>();
		mapWithCap = new MyHashMap<>(4, MyHashMap.DEFAULT_LOAD_FACTOR);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPut_nullKey() {
		testMap.put(null, TEST_VAL);
	}

	@Test
	public void testKeys_nonEmptyMap() {
		// You don't have to use array list 
		// This test will work with any object that implements List
		List<String> expectedKeys = new ArrayList<>(5);
		for(int i = 0; i < 5; i++) {
			// key + i is used to differentiate keys since they must be unique
			testMap.put(TEST_KEY + i, TEST_VAL + i);
			expectedKeys.add(TEST_KEY + i);
		}
		List<String> resultKeys = testMap.keys();
		// we need to sort because hash map doesn't guarantee ordering
		Collections.sort(resultKeys);
		assertEquals(expectedKeys, resultKeys);
	}
	
	/* Add more of your tests below */
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCapacity() {
		testMap = new MyHashMap<>(-1, MyHashMap.DEFAULT_LOAD_FACTOR);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidLoadFactor() {
		testMap = new MyHashMap<>(MyHashMap.DEFAULT_INITIAL_CAPACITY, 0);
	}

	@Test
	public void testPut() {
		for (int i = 0; i < 10; i++) {
			testMap.put(TEST_KEY + i, TEST_VAL + i);
		}
		assertEquals(10, testMap.size());
	}

	@Test
	public void testPutDuplicate() {
		for (int i = 0; i < 10; i++) {
			testMap.put(TEST_KEY + i, TEST_VAL + i);
		}
		for (int i = 0; i < 10; i++) {
			testMap.put(TEST_KEY + i, TEST_VAL + i);
		}
		assertEquals(10, testMap.size());
	}

	@Test
	public void testReplace() {
		for (int i = 0; i < 10; i++) {
			testMap.put(TEST_KEY + i, TEST_VAL + i);
		}
		for (int i = 0; i < 10; i++) {
			testMap.replace(TEST_KEY + i, TEST_VAL + i + 10);
		}
		List<String> expectedValue = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			expectedValue.add(TEST_VAL + i + 10);
		}
		List<String> resultValue = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			resultValue.add(testMap.get(TEST_KEY + i));
		}
		assertEquals(expectedValue, resultValue);
	}

	@Test
	public void testSet() {
		for (int i = 0; i < 10; i++) {
			testMap.set(TEST_KEY + i, TEST_VAL + i);
		}
		assertEquals(10, testMap.size());

		for (int i = 0; i < 10; i++) {
			testMap.set(TEST_KEY + i, TEST_VAL + i + 10);
		}
		assertEquals(10, testMap.size());
		for (int i = 0; i < 10; i++) {
			testMap.set(TEST_KEY + (i + 5), TEST_VAL + i + 10);
		}
		assertEquals(15, testMap.size());
	}

	@Test
	public void testRemove() {
		for (int i = 0; i < 10; i++) {
			testMap.set(TEST_KEY + i, TEST_VAL + i);
		}
		for (int i = 0; i < 10; i++) {
			testMap.remove(TEST_KEY + i);
		}
		assertEquals(0, testMap.size());
		assertTrue(testMap.isEmpty());
	}

	@Test
	public void testManyKeys() {
		for (int i = 0; i < 10000; i++) {
			testMap.put(TEST_KEY + i, TEST_VAL + i);
		}
		assertEquals(10000, testMap.size());
		for (int i = 0; i < 10000; i++) {
			assertTrue(testMap.containsKey(TEST_KEY + i));
		}
	}

	@Test
	public void testContainsKey() {
		for (int i = 0; i < 100; i++) {
			testMap.put(TEST_KEY + i, TEST_VAL + i);
		}
		for (int i = 0; i < 100; i++) {
			assertTrue(testMap.containsKey(TEST_KEY + i));
		}
	}

	@Test
	public void testGet() {
		for (int i = 0; i < 100; i++) {
			testMap.put(TEST_KEY + i, TEST_VAL + i);
		}
		for (int i = 0; i < 100; i++) {
			assertEquals(TEST_VAL + i, testMap.get(TEST_KEY + i));
		}
	}
}