package cse12pa1student;

import java.util.Collection;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BasketTest {

	public static Collection<Object[]> BAGNUMS =
			Arrays.asList(new Object[][] { {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12} });
	private int bagType;

	public BasketTest(int bagType) {
		super();
		this.bagType = bagType;
	}

	@Parameterized.Parameters(name = "Basket{index}")
	public static Collection<Object[]> bags() {
		return BAGNUMS;
	}
	
	private Basket makeBasket() {
		switch(this.bagType) {
			case 0: return new Basket0();
			case 1: return new Basket1();
			case 2: return new Basket2();
			case 3: return new Basket3();
			case 4: return new Basket4();
			case 5: return new Basket5();
			case 6: return new Basket6();
			case 7: return new Basket7();
			case 8: return new Basket8();
			case 9: return new Basket9();
			case 10: return new Basket10();
			case 11: return new Basket11();
			case 12: return new Basket12();
		}
		return null;
	}
	
	@Test
	public void addedHasCount1() {
		Basket basketToTest = makeBasket();

		Item i = new Item("Shampoo", 5);
		basketToTest.addToBasket(i);
		assertEquals(1, basketToTest.count());
	}
	
	@Test
	public void testCount() {
		Basket basketToTest = makeBasket();

		assertEquals(0, basketToTest.count());

		for (int i = 0; i < 10; i++) {
			Item item = new Item("Shampoo", i);
			basketToTest.addToBasket(item);
		}

		assertEquals(10, basketToTest.count());

		for (int i = 0; i < 10; i++) {
			Item item = new Item("Shampoo", 100);
			basketToTest.addToBasket(item);
		}

		assertEquals(20, basketToTest.count());
	}

	@Test
	public void testCountItem() {
		Basket basketToTest = makeBasket();

		basketToTest.addToBasket(new Item("Shampoo", 0));
		for (int i = 0; i < 10; i++) {
			Item item = new Item("Shampoo", 5);
			basketToTest.addToBasket(item);
		}

		assertEquals(10, basketToTest.countItem(new Item("Shampoo", 5)));
		assertEquals(1, basketToTest.countItem(new Item("Shampoo", 0)));
	}

	@Test
	public void testTotalCost() {
		Basket basketToTest = makeBasket();

		basketToTest.addToBasket(new Item("Shampoo", 0));
		for (int i = 0; i < 10; i++) {
			Item item = new Item("Shampoo", 5);
			basketToTest.addToBasket(item);
		}

		assertEquals(50, basketToTest.totalCost());

		basketToTest.addToBasket(new Item("Shampoo", 100));
		assertEquals(150, basketToTest.totalCost());
	}

	@Test
	public void testAddToBasket() {
		Basket basketToTest = makeBasket();

		basketToTest.addToBasket(new Item("Shampoo", 0));

		for (int i = 0; i < 1000; i++) {
			Item item = new Item("Shampoo", 5);
			basketToTest.addToBasket(item);
		}

		assertEquals(1001, basketToTest.count());

		basketToTest.addToBasket(new Item("Shampoo", 100));
		assertEquals(1002, basketToTest.count());

		for (int i = 1000; i < 2000; i++) {
			Item item = new Item("Shampoo", i);
			basketToTest.addToBasket(item);
			assertEquals(1, basketToTest.countItem(new Item("Shampoo", i)));
		}

		assertEquals(2002, basketToTest.count());
		basketToTest.addToBasket(null);
		assertEquals(2002, basketToTest.count());
	}

	@Test
	public void testRemoveFromBasket() {
		Basket basketToTest = makeBasket();

		basketToTest.addToBasket(new Item("Shampoo", 0));
		basketToTest.addToBasket(new Item("Shampoo", 100));

		for (int i = 0; i < 1000; i++) {
			Item item = new Item("Shampoo", 5);
			basketToTest.addToBasket(item);
		}

		for (int i = 1000; i < 2000; i++) {
			Item item = new Item("Shampoo", i);
			basketToTest.addToBasket(item);
		}

		basketToTest.removeFromBasket(new Item("Shampoo", 0));
		assertEquals(2001, basketToTest.count());
		assertEquals(0, basketToTest.countItem(new Item("Shampoo", 0)));
		
		basketToTest.removeFromBasket(new Item("Shampoo", 200));
		assertEquals(2001, basketToTest.count());
		
		basketToTest.removeFromBasket(null);
		assertEquals(2001, basketToTest.count());

		for (int i = 1000; i < 2000; i++) {
			Item item = new Item("Shampoo", i);
			basketToTest.removeFromBasket(item);
			assertEquals(0, basketToTest.countItem(item));
		}
		assertEquals(1001, basketToTest.count());
		assertEquals(1000, basketToTest.countItem(new Item("Shampoo", 5)));

		for (int i = 0; i < 1000; i++) {
			Item item = new Item("Shampoo", 5);
			basketToTest.removeFromBasket(item);
		}
		assertEquals(1, basketToTest.count());

		basketToTest.removeFromBasket(new Item("Shampoo", 100));
		assertEquals(0, basketToTest.count());
	}

	@Test
	public void testRemoveAllFromBasket() {
		Basket basketToTest = makeBasket();

		basketToTest.addToBasket(new Item("Shampoo", 0));
		basketToTest.addToBasket(new Item("Shampoo", 100));

		for (int i = 0; i < 1000; i++) {
			Item item = new Item("Shampoo", 5);
			basketToTest.addToBasket(item);
		}

		for (int i = 1000; i < 2000; i++) {
			Item item = new Item("Shampoo", i);
			basketToTest.addToBasket(item);
		}

		basketToTest.removeAllFromBasket(new Item("Shampoo", 0));
		assertEquals(2001, basketToTest.count());
		assertEquals(0, basketToTest.countItem(new Item("Shampoo", 0)));

		basketToTest.removeAllFromBasket(new Item("Shampoo", 5));
		assertEquals(1001, basketToTest.count());
		assertEquals(0, basketToTest.countItem(new Item("Shampoo", 5)));
	}

	@Test
	public void testEmpty() {
		Basket basketToTest = makeBasket();

		basketToTest.addToBasket(new Item("Shampoo", 0));
		for (int i = 0; i < 1000; i++) {
			Item item = new Item("Shampoo", 5);
			basketToTest.addToBasket(item);
		}

		basketToTest.addToBasket(new Item("Shampoo", 100));

		for (int i = 1000; i < 2000; i++) {
			Item item = new Item("Shampoo", i);
			basketToTest.addToBasket(item);
		}

		basketToTest.empty();
		assertEquals(0, basketToTest.count());
	}	
}
