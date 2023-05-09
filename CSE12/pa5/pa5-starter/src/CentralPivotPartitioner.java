// You can (and should) add "implements Partitioner" below once you have the implementation ready
public class CentralPivotPartitioner implements Partitioner{

    private void swap(String[] strs, int i, int j) {
        String temp = strs[i];
        strs[i] = strs[j];
        strs[j] = temp;
    }

    @Override
    public int partition(String[] strs, int low, int high) {
        if (high - low <= 1) { return low; }
            
        int pivotIndex = (high + low) / 2;
        this.swap(strs, high-1, pivotIndex);
        pivotIndex = high - 1;
        high -= 2;

        while (low <= high) {
            while (strs[low].compareTo(strs[pivotIndex]) < 0) {
                low += 1;
            }
            while (strs[high].compareTo(strs[pivotIndex]) >= 0) {
                high -= 1;
                if (low >= high) { break; }
            } 
            
            if (low >= high) { break; }

            this.swap(strs, low, high);
        }

        this.swap(strs, low, pivotIndex);

        return low;
    }
}
