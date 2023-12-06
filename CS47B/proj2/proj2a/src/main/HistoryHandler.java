package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;


public class HistoryHandler extends NgordnetQueryHandler {
    
    private NGramMap ngm;

    public HistoryHandler(NGramMap ngm) {
        super();
        this.ngm = ngm;
    }
    
    @Override
    public String handle(NgordnetQuery q) {

        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>(words);

        for (String word : words) {
            lts.add(this.ngm.weightHistory(word, startYear, endYear));
        }


        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }
}
