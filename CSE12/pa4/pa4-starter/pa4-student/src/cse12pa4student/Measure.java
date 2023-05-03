package cse12pa4student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import static cse12pa4mysteries.Mysteries.*;

public class Measure {


	public static List<Measurement> measure(String[] toRun, int startN, int stopN) {
		/** TODO **/
		
		List<Measurement> measurements = new ArrayList<>();

		//Example call to mystery method
		for (String i : toRun) { 
			switch (i) {
				case "A":
					for (int j = startN; j <= stopN; j++) {
						long startTime = System.nanoTime();
						mysteryA(j);
						long endTime = System.nanoTime();
						measurements.add(new Measurement("A", j, endTime - startTime));
					}
					break;
				case "B":
					for (int j = startN; j <= stopN; j++) {
						long startTime = System.nanoTime();
						mysteryB(j);
						long endTime = System.nanoTime();
						measurements.add(new Measurement("B", j, endTime - startTime));
					}
					break;
				case "C":
					for (int j = startN; j <= stopN; j++) {
						long startTime = System.nanoTime();
						mysteryC(j);
						long endTime = System.nanoTime();
						measurements.add(new Measurement("C", j, endTime - startTime));
					}
					break;
				case "D":
					for (int j = startN; j <= stopN; j++) {
						long startTime = System.nanoTime();
						mysteryD(j);
						long endTime = System.nanoTime();
						measurements.add(new Measurement("D", j, endTime - startTime));
					}
					break;
				case "E":
					for (int j = startN; j <= stopN; j++) {
						long startTime = System.nanoTime();
						mysteryE(j);
						long endTime = System.nanoTime();
						measurements.add(new Measurement("E", j, endTime - startTime));
					}
					break;
				case "F":
					for (int j = startN; j <= stopN; j++) {
						long startTime = System.nanoTime();
						mysteryF(j);
						long endTime = System.nanoTime();
						measurements.add(new Measurement("F", j, endTime - startTime));
					}
					break;
			}
		}
		
		return measurements;
	}
	

	public static String measurementsToCSV(List<Measurement> toConvert) {
		/** TODO **/
		String csv = "name,n,nanoseconds\n";
		for (Measurement i : toConvert) {
			csv += i.name + "," + i.valueOfN + "," + i.nanosecondsToRun + "\n";
		}

		try (PrintWriter writer = new PrintWriter (new File ("measurements.csv"))) {
			writer.write (csv);
			System.out.println ("done!");
		} catch (FileNotFoundException e) {
			System.out.println (e.getMessage());
		}

		return csv;
	}
	
	/* Add any helper methods you find useful */
		
}
