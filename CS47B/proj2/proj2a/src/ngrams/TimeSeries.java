package ngrams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for (int i = startYear; i <= endYear; i++) {
            if (ts.containsKey(i)) {
                this.put(i, ts.get(i));
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        List<Integer> yearList = new ArrayList<>(this.keySet());
        yearList.sort(Comparator.naturalOrder());
        return yearList;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Double> dataList = new ArrayList<>();
        List<Integer> yearList = this.years();
        for (Integer year : yearList) {
            dataList.add(this.get(year));
        }
        return dataList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        int minYear;
        int maxYear;

        if (ts.years().size() == 0 && this.years().size() == 0) {
            return new TimeSeries();
        } else if (ts.years().size() == 0 || this.years().size() == 0) {
            minYear = this.years().size() == 0 ? ts.firstKey() : this.firstKey();
            maxYear = this.years().size() == 0 ? ts.lastKey() : this.lastKey();
        } else {
            minYear = Math.min(ts.firstKey(), this.firstKey());
            maxYear = Math.max(ts.lastKey(), this.lastKey());
        }

        TimeSeries paap = new TimeSeries();
        for (int year = minYear; year <= maxYear; year++) {
            if (ts.containsKey(year) || this.containsKey(year)) {
                paap.put(year, ts.getOrDefault(year, 0.0) + this.getOrDefault(year, 0.0));
            }
        }
        return paap;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries quotientTS = new TimeSeries();
        for (Integer year : this.years()) {
            if (!ts.containsKey(year)) {
                throw new IllegalArgumentException(
                        "TS is missing a year that exists in this TimeSeries"
                    );
            }
            quotientTS.put(year, this.get(year) / ts.get(year));
        }
        return quotientTS;
    }
}
