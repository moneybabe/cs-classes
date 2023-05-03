package cse12pa4student;

import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		List<Measurement> measurements = Measure.measure(new String[] {"C"}, 0, 1800);
		Measure.measurementsToCSV(measurements);
	}
}
