
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection; 
import java.util.NoSuchElementException;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestLists {

	public static Collection<Object[]> LISTNUMS =
			Arrays.asList(new Object[][] { {"Linked"}, {"Array"} });
	private String listType;

	public TestLists(String listType) {
		super();
		this.listType = listType;
	}

	@Parameterized.Parameters(name = "{0}List")
	public static Collection<Object[]> bags() {
		return LISTNUMS;
	}

	private <E> MyList<E> makeList(E[] contents) {
		switch (this.listType) {
		case "Linked":
			return new LinkedGL<E>(contents);
		case "Array":
			return new ArrayGL<E>(contents);
		}
		return null;
	}

  // Don't change code above this line, it ensures the autograder works as
  // expected


  // This is a sample test; you can keep it, change it, or remove it as you like.
  // Note that it uses the method `assertArrayEquals`, which you should use to
  // test equality of arrays in this PA.
	@Test
	public void testSimpleToArray() {
		// Using the generic list to create an Integer list
		Integer[] int_input = {1, 2, 3};
		MyList<Integer> int_s = makeList(int_input);
		assertArrayEquals(int_input, int_s.toArray());
		
		// Using the generic list to create a String list
		String[] string_input = {"a", "b", "c"};
		MyList<String> string_s = makeList(string_input);
		assertArrayEquals(string_input, string_s.toArray());
	}

	@Test
	public void testNestedToArray() {
		Integer[][] int_input = {{1}, {2}, {3}};
		MyList<Integer[]> int_s = makeList(int_input);
		assertArrayEquals(int_input, int_s.toArray());
		
		// Using the generic list to create a String list
		String[][] string_input = {{"a", ""}, {"b", "abc"}, {"c"}};
		MyList<String[]> string_s = makeList(string_input);
		assertArrayEquals(string_input, string_s.toArray());
	}

	@Test
	public void testIsEmpty() {
		Integer[] int_input = {};
		MyList<Integer> int_s = makeList(int_input);
		assertTrue(int_s.isEmpty());
		
		// Using the generic list to create a String list
		String[] string_input = {};
		MyList<String> string_s = makeList(string_input);
		assertTrue(string_s.isEmpty());

		Integer[] int_input2 = {1, 2, 3};
		MyList<Integer> int_s2 = makeList(int_input2);
		assertFalse(int_s2.isEmpty());

		String[] string_input2 = {"a", "b", "c"};
		MyList<String> string_s2 = makeList(string_input2);
		assertFalse(string_s2.isEmpty());

		Integer[] int_input3 = {null, null, null};
		MyList<Integer> int_s3 = makeList(int_input3);
		assertFalse(int_s3.isEmpty());
	}
}