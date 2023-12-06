package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;
import java.util.TreeMap;

import java.util.ArrayList;
import java.util.Comparator;

public class HyponymsHandler extends NgordnetQueryHandler {
    
    private NGramMap ngm;
    private WordNetMap wordNetMap;

    public HyponymsHandler(NGramMap ngm, WordNetMap wordNetMap) {
        super();
        this.ngm = ngm;
        this.wordNetMap = wordNetMap;
    }
    
    private Double getCount(String word, int startYear, int endYear) {
        TimeSeries ts = this.ngm.countHistory(word, startYear, endYear);
        List<Double> countList = new ArrayList<Double>(ts.values());
        Double sum = 0.0;
        for (Double count : countList) {
            sum += count;
        }
        return sum;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();

        List<String> hyponyms = this.wordNetMap.getHyponyms(words);
        if (k <= 0) {
            return hyponyms.toString();
        }
        TreeMap<Double, List<String>> count2Word = new TreeMap<>();
        for (String hyponym : hyponyms) {
            Double count = this.getCount(hyponym, startYear, endYear);
            count2Word.putIfAbsent(count, new ArrayList<String>());
            count2Word.get(count).add(hyponym);
        }
        count2Word.remove(0.0);
        for (List<String> value : count2Word.values()) {
            value.sort(Comparator.naturalOrder());
        }
        List<String> returnList = new ArrayList<String>();
        int treeSize = count2Word.size();
        for (int i = 0; i < treeSize; i++) {
            List<String> highestCountWords = count2Word.pollLastEntry().getValue();
            returnList.addAll(highestCountWords);
        }
        returnList = returnList.size() > k ? returnList.subList(0, k) : returnList;
        returnList.sort(Comparator.naturalOrder());
        return returnList.toString();
    }
}
