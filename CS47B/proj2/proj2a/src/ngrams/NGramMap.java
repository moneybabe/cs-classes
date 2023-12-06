package ngrams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private HashMap<String, TimeSeries> WordToTS = new HashMap<>();
    private TimeSeries WordCountsTS = new TimeSeries();
    
    public static void main(String[] args) {
        NGramMap ngm = new NGramMap("./data/ngrams/very_short.csv", "./data/ngrams/total_counts.csv");
        TimeSeries request2005to2008 = ngm.countHistory("request");
        System.out.println(request2005to2008);
    }

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {

        try (BufferedReader br = new BufferedReader(new FileReader(wordsFilename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\s+");
                
                if (fields.length != 4) {
                    throw new RuntimeException("Incorrect number of fields in line: " + line);
                } else {
                    String word = fields[0];
                    Integer year = Integer.parseInt(fields[1]);
                    Double count = Double.parseDouble(fields[2]);
    
                    if (!WordToTS.containsKey(word)) {
                        WordToTS.put(word, new TimeSeries());
                    }
                    WordToTS.get(word).put(year, count);
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(countsFilename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                
                if (fields.length != 4) {
                    throw new RuntimeException("Incorrect number of fields in line: " + line);
                } else {
                    Integer year = Integer.parseInt(fields[0]);
                    Double count = Double.parseDouble(fields[1]);

                    WordCountsTS.put(year, count);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        if (!this.WordToTS.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries wordTS = this.WordToTS.get(word);
        return new TimeSeries(wordTS, startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        if (!this.WordToTS.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries wordTS = this.WordToTS.get(word);
        return (TimeSeries) wordTS.clone();
    }
    
    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return (TimeSeries) this.WordCountsTS.clone();
    }
    
    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if (!this.WordToTS.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries allWeightHistory = this.weightHistory(word);
        return new TimeSeries(allWeightHistory, startYear, endYear);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if (!this.WordToTS.containsKey(word)) {
            return new TimeSeries();
        }
        return this.WordToTS.get(word).dividedBy(this.totalCountHistory());
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries allSummedWeightHistory = this.summedWeightHistory(words);
        return new TimeSeries(allSummedWeightHistory, startYear, endYear);
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries history = new TimeSeries();
        for (String word : words) {
            history = history.plus(this.weightHistory(word));
        }
        return history;
    }

}
