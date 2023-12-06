package main;

import browser.NgordnetServer;
import ngrams.NGramMap;

public class Main {
    public static void main(String[] args) {
        String WORDFILE = "./data/ngrams/top_14377_words.csv";
        String COUNTEFILE = "./data/ngrams/total_counts.csv";
        String HYPONYMFILE = "./data/wordnet/hyponyms.txt";
        String SYNSETFILE = "./data/wordnet/synsets.txt";
        
        NgordnetServer hns = new NgordnetServer();
        NGramMap ngm = new NGramMap(WORDFILE, COUNTEFILE);
        WordNetMap wordNetMap = new WordNetMap(HYPONYMFILE, SYNSETFILE);

        hns.startUp();
        hns.register("history", new DummyHistoryHandler());
        hns.register("historytext", new DummyHistoryTextHandler());
        hns.register("hyponyms", new HyponymsHandler(ngm, wordNetMap));

        System.out.println("Finished server startup! Visit http://localhost:4567/ngordnet.html");
    }
}