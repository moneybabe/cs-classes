package main;

import browser.NgordnetQueryHandler;
import ngrams.NGramMap;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        
        NGramMap ngm = new NGramMap(wordFile, countFile);
        WordNetMap wordNetMap = new WordNetMap(hyponymFile, synsetFile);
        return new HyponymsHandler(ngm, wordNetMap);
    }
}
