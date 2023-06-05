import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class TransformerTest {
    
    @Test
    public void testTransform() {
        int[] arr = {1, 2, 3};
        int[] result = Transformer.timesTwo(arr);
        assertEquals(2, result[0]);
        assertEquals(4, result[1]);
        assertEquals(6, result[2]);
    }

    @Test
    public void testTranformAddOriginal() {
        int[] arr = {1, 2, 3};
        int[] result = Transformer.timesTwo(arr);
        
        assertEquals(1 * 3, result[0] + arr[0]);
        assertEquals(2 * 3, result[1] + arr[1]);
        assertEquals(3 * 3, result[2] + arr[2]);
    }
}
