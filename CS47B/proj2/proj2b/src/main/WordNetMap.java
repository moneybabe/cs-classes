package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class WordNetMap {
    private HashMap<String, List<Integer>> word2Ids = new HashMap<String, List<Integer>>();
    private DirectedGraph<Integer, String> hyponymsGraph = new DirectedGraph<Integer, String>();

    public WordNetMap(String hyponymsFilename, String synsetsFilename) {

        try (BufferedReader br = new BufferedReader(new FileReader(hyponymsFilename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                Integer daddy = Integer.parseInt(fields[0]);
                this.hyponymsGraph.addNode(daddy);
                for (int i = 1; i < fields.length; i++) {
                    Integer kid = Integer.parseInt(fields[i]);
                    this.hyponymsGraph.addNode(kid);
                    this.hyponymsGraph.addKid(daddy, kid);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(synsetsFilename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                Integer id = Integer.parseInt(fields[0]);
                String[] words = fields[1].split(" ");
                for (String word : words) {
                    this.word2Ids.putIfAbsent(word, new ArrayList<Integer>());
                    this.word2Ids.get(word).add(id);
                    this.hyponymsGraph.addNode(id);
                    this.hyponymsGraph.addValue(id, word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getHyponyms(String word) {
        List<Integer> ids = this.word2Ids.getOrDefault(word, new ArrayList<Integer>());
        Set<String> hyponymsSet = new HashSet<String>();
        hyponymsSet.addAll(this.hyponymsGraph.getValues(ids));
        for (Integer id : ids) {
            hyponymsSet.addAll(this.hyponymsGraph.getAllKidsValues(id));
        }
        List<String> hyponymsList = new ArrayList<String>(hyponymsSet);
        hyponymsList.sort(Comparator.naturalOrder());
        return hyponymsList;
    }
    
    public List<String> getHyponyms(List<String> words) {
        Set<String> hyponymsSet = new HashSet<String>(this.getHyponyms(words.get(0)));
        for (String word : words) {
            List<String> nextHyponyms = this.getHyponyms(word);
            Set<String> nextHyponymsSet = new HashSet<String>(nextHyponyms);
            hyponymsSet.retainAll(nextHyponymsSet);
        }
        List<String> hyponymsList = new ArrayList<String>(hyponymsSet);
        hyponymsList.sort(Comparator.naturalOrder());
        return hyponymsList;
    }

}
