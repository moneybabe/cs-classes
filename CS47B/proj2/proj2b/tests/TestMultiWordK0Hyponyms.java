import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import main.AutograderBuddy;
import main.WordNetMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;

/** Tests the case where the list of words is length greater than 1, but k is still zero. */
public class TestMultiWordK0Hyponyms {
    // this case doesn't use the NGrams dataset at all, so the choice of files is irrelevant
    public static final String WORDS_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/ngrams/very_short.csv";
    public static final String LARGE_WORDS_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/ngrams/top_14377_words.csv";
    public static final String TOTAL_COUNTS_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/ngrams/total_counts.csv";
    public static final String SMALL_SYNSET_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/wordnet/hyponyms.txt";
    public static final String GRADESCOPE_HYPONYM_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/wordnet/hyponymsGradescope.txt";
    public static final String GRADESCOPE_SYNSET_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/wordnet/synsetsGradescope.txt";
    public static final String GRADESCOPE_WORDS_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/ngrams/gradescope_words.csv";

    /** This is an example from the spec.*/
    @Test
    public void testOccurrenceAndChangeK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = List.of("occurrence", "change");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testChangeK0() {
        WordNetMap wordNetMap = new WordNetMap(SMALL_HYPONYM_FILE, SMALL_SYNSET_FILE);
        String actual = wordNetMap.getHyponyms(List.of("change", "occurrence")).toString();
        String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        assertEquals(expected, actual);
    }
    
    @Test
    public void testVideoRecordingK0() {
        WordNetMap wordNetMap = new WordNetMap(LARGE_HYPONYM_FILE, LARGE_SYNSET_FILE);
        String actual = wordNetMap.getHyponyms(List.of("video", "recording")).toString();
        String expected = "[video, video_recording, videocassette, videotape]";
        assertEquals(expected, actual);
    }
    
    @Test
    public void testPastryTartK0() {
        WordNetMap wordNetMap = new WordNetMap(LARGE_HYPONYM_FILE, LARGE_SYNSET_FILE);
        String actual = wordNetMap.getHyponyms(List.of("pastry", "tart")).toString();
        String expected = "[apple_tart, lobster_tart, quiche, quiche_Lorraine, tart, tartlet]";
        assertEquals(expected, actual);
    }
    
    @Test
    public void testFoodCake() {
        NgordnetQueryHandler handler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
                List<String> words = List.of("cake", "food");
                
        NgordnetQuery nq = new NgordnetQuery(words, 1950, 1990, 5);
        String actual = handler.handle(nq);
        String expected = "[cake, cookie, kiss, snap, wafer]";
        assertThat(actual).isEqualTo(expected);
        
    }
    
    @Test
    void testALot() {
        NgordnetQueryHandler handler = AutograderBuddy.getHyponymHandler(
                LARGE_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);

        List<String> words = List.of("computer");
        NgordnetQuery nq = new NgordnetQuery(words, 1950, 2020, 9);
        String actual = handler.handle(nq);
        String expected = "[client, computer, guest, host, node, portal, server, site, website]";
        assertThat(actual).isEqualTo(expected);

        words = List.of("antibody");
        nq = new NgordnetQuery(words, 1920, 2020, 9);
        actual = handler.handle(nq);
        expected = "[antibody, immunoglobulin, monoclonal]";
        assertThat(actual).isEqualTo(expected);

        words = List.of("buff");
        nq = new NgordnetQuery(words, 1920, 2020, 9);
        actual = handler.handle(nq);
        expected = "[buffer, fan, lover]";
        assertThat(actual).isEqualTo(expected);

        words = List.of("buff", "antibody");
        nq = new NgordnetQuery(words, 1920, 2020, 9);
        actual = handler.handle(nq);
        expected = "[]";
        assertThat(actual).isEqualTo(expected);
        
        words = List.of("fire", "flame");
        nq = new NgordnetQuery(words, 1900, 2020, 9);
        actual = handler.handle(nq);
        expected = "[blazing, fire, flame, flare, ignition]";
        assertThat(actual).isEqualTo(expected);

        words = List.of("fire", "firing");
        nq = new NgordnetQuery(words, 1900, 2020, 20);
        actual = handler.handle(nq);
        expected = "[battery, burst, cover, fire, firing, ignition]";
        assertThat(actual).isEqualTo(expected);
        
        words = List.of("car", "auto");
        nq = new NgordnetQuery(words, 1900, 2020, 20);
        actual = handler.handle(nq);
        expected = "[ambulance, auto, automobile, bus, cab, car, compact, electric, heap, machine, taxi, wagon]";
        assertThat(actual).isEqualTo(expected);
        
        words = List.of("car", "cab", "auto");
        nq = new NgordnetQuery(words, 1900, 2020, 20);
        actual = handler.handle(nq);
        expected = "[cab, taxi]";
        assertThat(actual).isEqualTo(expected);
        
        words = List.of("arm", "gun");
        nq = new NgordnetQuery(words, 1900, 2020, 20);
        actual = handler.handle(nq);
        expected = "[automatic, cannon, gun, piece, pistol, revolver, rifle, rod, shotgun]";
        assertThat(actual).isEqualTo(expected);

        words = List.of("show", "program");
        nq = new NgordnetQuery(words, 1900, 2020, 20);
        actual = handler.handle(nq);
        expected = "[bill, broadcast, episode, installment, news, pilot, play, program, programme, serial, series, special]";
        assertThat(actual).isEqualTo(expected);

        words = List.of("show", "program", "serial");
        nq = new NgordnetQuery(words, 1900, 2020, 20);
        actual = handler.handle(nq);
        expected = "[serial, series]";
        assertThat(actual).isEqualTo(expected);

        words = List.of("power", "mind");
        nq = new NgordnetQuery(words, 1900, 2020, 20);
        actual = handler.handle(nq);
        expected = "[brain, intellect, judgement, judgment, mind, nous]";
        assertThat(actual).isEqualTo(expected);

        words = List.of("power", "brain");
        nq = new NgordnetQuery(words, 1900, 2020, 20);
        actual = handler.handle(nq);
        expected = "[brain, genius, mentality, mind, nous, wit]";
        assertThat(actual).isEqualTo(expected);
        
        words = List.of("change", "rise");
        nq = new NgordnetQuery(words, 1900, 2020, 30);
        actual = handler.handle(nq);
        expected = "[advance, ascending, ascent, climb, elevation, hike, lift, mount, procession, rise, scaling, upgrade, wave]";
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    void testSmallRandomK0() {
        NgordnetQueryHandler handler = AutograderBuddy.getHyponymHandler(
            LARGE_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
            int length = 10;
            boolean useLetters = true;
            boolean useNumbers = false;
            String word;
            for (int i = 0; i < 10000; i++) {
                word = RandomStringUtils.random(length, useLetters, useNumbers);
                NgordnetQuery nq = new NgordnetQuery(List.of(word), 1470, 2019, 0);
                handler.handle(nq);
            }
            
        }
        
        @Test
        void testGradescope() {
            NgordnetQueryHandler handler = AutograderBuddy.getHyponymHandler(
                GRADESCOPE_WORDS_FILE, TOTAL_COUNTS_FILE, GRADESCOPE_SYNSET_FILE, GRADESCOPE_HYPONYM_FILE);
        NgordnetQuery nq = new NgordnetQuery(List.of("DDDD"), 1450, 1500, 3);
        String expected = "[DDDD, OOOO, PPPP]";
        String actual = handler.handle(nq);
        assertThat(actual).isEqualTo(expected);
        
        nq = new NgordnetQuery(List.of("BBBB"), 1450, 1500, 3);
        expected = "[BBBB, FFFF, GGGG]";
        actual = handler.handle(nq);
        assertThat(actual).isEqualTo(expected);

    }

}   
