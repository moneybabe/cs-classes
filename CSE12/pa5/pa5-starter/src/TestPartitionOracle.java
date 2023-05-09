import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
/**
 * This is an example of how to implement the Partitioner interface to implement
 * a concrete Partitioner. You can use this bad implementation to test your PartitionOracle,
 * to ensure that it works in detecting a bad Partitioner. You should add a correct implementation
 * of a Partitioner here, maybe one from class, to verify that your PartitionOracle also works
 * correctly on good implementations. Once you implement part 2, you can also test those Partitioner
 * implementations here as well.
 * 
 */
class CopyFirstElementPartition implements Partitioner {
    public int partition(String[] strs, int low, int high) {
        if (high - low < 1)
            return 0;
        for (int i = 0; i < strs.length; i += 1) {
            strs[i] = strs[0];
        }
        return 0;
    }
}

public class TestPartitionOracle {
    // @Test
    // public void testCopyFirstElementPartition() {
    //     CounterExample ce = PartitionOracle.findCounterExample(new CopyFirstElementPartition());
    //     System.out.println(ce);
    //     assertNotNull(ce);
    // }

    @Test
    public void testIsValidPartitionResult() {
        String[] before = new String[] { "a", "e", "f", "d", "b", "c", "g" };
        String[] after = new String[] { "a", "b", "c", "d", "e", "f", "g" };
        assertNull(PartitionOracle.isValidPartitionResult(before, 0, 7, 5, after));

        String[] before2 = new String[] { "a", "b", "c", "d", "e", "f", "g" };
        String[] after2 = new String[] { "a", "e", "f", "d", "1", "c", "g" };
        assertEquals("before and after are not the same", 
                    PartitionOracle.isValidPartitionResult(before2, 0, 7, 5, after2));

        String[] before3 = new String[] { "a", "b", "c", "d", "e", "f", "g" };
        String[] after3 = new String[] { "a", "e", "f", "d", "b", "c", "g" };
        assertEquals("pivot is not between low and high", 
                    PartitionOracle.isValidPartitionResult(before3, 0, 5, 5, after3));

        String[] before4 = new String[] { "a", "b", "c", "d", "e", "f", "g" };
        String[] after4 = new String[] { "a", "e", "f", "d", "b", "c", "g" };
        assertEquals("elements after high are not the same", 
                    PartitionOracle.isValidPartitionResult(before4, 0, 5, 2, after4));

        String[] before5 = new String[] { "a", "b", "c", "d", "e", "f", "g" };
        String[] after5 = new String[] { "a", "e", "f", "d", "b", "c", "g" };
        assertEquals("elements before low are not the same", 
                    PartitionOracle.isValidPartitionResult(before5, 2, 7, 5, after5));

        String[] before6 = new String[] { "a", "b", "c", "d", "e", "f", "g" };
        String[] after6 = new String[] { "a", "e", "f", "d", "b", "c", "g" };
        assertEquals("elements before pivot are not less than pivot", 
                    PartitionOracle.isValidPartitionResult(before6, 0, 7, 5, after6));
    }

    @Test
    public void testGenerateInput() {
        Random r = new Random();
        int testTrials = 50;
        int length;
        String[] generateArr;

        for (int i = 0; i < testTrials; i++) {
            length = r.nextInt(100);
            generateArr = PartitionOracle.generateInput(length);
            assertEquals(length, generateArr.length);
            for (int j = 0; j < length; j++) {
                assertTrue(generateArr[j].length() == 1);
                assertTrue(generateArr[j].charAt(0) >= 'A' && generateArr[j].charAt(0) <= 'Z');
            }
        }

    }

    @Test 
    public void testFindCounterExample() {
        CounterExample ce = PartitionOracle.findCounterExample(new FirstElePivotPartitioner());
        assertNull(ce);

        ce = PartitionOracle.findCounterExample(new CentralPivotPartitioner());
        assertNull(ce);

        ce = PartitionOracle.findCounterExample(new CopyFirstElementPartition());
        assertNotNull(ce);

        ce = PartitionOracle.findCounterExample(new WebPartitioner());
        assertNull(ce);
    }
}
