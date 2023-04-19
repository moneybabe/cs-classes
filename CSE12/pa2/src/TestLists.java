
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
	public void testConstructor() {
		String[] string_input = new String[100000];
		for (int i = 0; i < 100000; i++) {
			string_input[i] = Integer.toString(i);
		}

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

	@Test
	public void testTransformAll() {

		String[] string_input = {"a", "b", "c"};
		MyList<String> string_s = makeList(string_input);
		string_s.transformAll(new CSE12Transformer());
		String[] string_expected = {"CSE12a", "CSE12b", "CSE12c"};
		assertArrayEquals(string_expected, string_s.toArray());

		String[] string_input2 = {"abc", "def", "ghi"};
		MyList<String> string_s2 = makeList(string_input2);
		string_s2.transformAll(new LastLetterTransformer());
		String[] string_expected2 = {"c", "f", "i"};
		assertArrayEquals(string_expected2, string_s2.toArray());

		String[] string_input3 = {};
		MyList<String> string_s3 = makeList(string_input3);
		string_s3.transformAll(new LastLetterTransformer());
		String[] string_expected3 = {};
		assertArrayEquals(string_expected3, string_s3.toArray());
	}

	@Test
	public void testChooseAll() {
		String[] string_input = {"a", "b", "c"};
		MyList<String> string_s = makeList(string_input);
		string_s.chooseAll(new ContainsSpaceChoose());
		String[] string_expected = {};
		assertArrayEquals(string_expected, string_s.toArray());

		String[] string_input2 = {"abc", "d ef", "gh i"};
		MyList<String> string_s2 = makeList(string_input2);
		string_s2.chooseAll(new ContainsSpaceChoose());
		String[] string_expected2 = {"d ef", "gh i"};
		assertArrayEquals(string_expected2, string_s2.toArray());

		String[] string_input3 = {"ab2c", "d ef", "gh 3i"};
		MyList<String> string_s3 = makeList(string_input3);
		string_s3.chooseAll(new ContainsNumberChoose());
		String[] string_expected3 = {"ab2c", "gh 3i"};
		assertArrayEquals(string_expected3, string_s3.toArray());

		String[] string_input4 = {"ab2c", "d ef", "gh 3i"};
		MyList<String> string_s4 = makeList(string_input4);
		string_s4.chooseAll(new ContainsNumberChoose());
		string_s4.chooseAll(new ContainsSpaceChoose());
		String[] string_expected4 = {"gh 3i"};
		assertArrayEquals(string_expected4, string_s4.toArray());

	}
}