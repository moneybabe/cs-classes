import static org.junit.Assert.*;
import org.junit.*;

public class ArrayTests {
  @Test
  public void failAvergeWithoutLowest() {
    double[] input1 = { 1.0, 2.0, 4.0 , 1.0 , 1.0};
    assertEquals(3.0, ArrayExamples.averageWithoutLowest(input1), 0.0001);
  }
  
  @Test
  public void passAverageWithoutLowest() {
    double[] input1 = { 1.0, 2.0, 4.0};
    assertEquals(3.0, ArrayExamples.averageWithoutLowest(input1), 0.0001);
  }
  
}
