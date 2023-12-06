import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import org.junit.jupiter.api.Test;
import main.AutograderBuddy;
import main.WordNetMap;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests the most basic case for Hyponyms where the list of words is one word long, and k = 0.*/
public class TestOneWordK0Hyponyms {
    // this case doesn't use the NGrams dataset at all, so the choice of files is irrelevant
    public static final String WORDS_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/ngrams/very_short.csv";
    public static final String TOTAL_COUNTS_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/ngrams/total_counts.csv";
    public static final String SMALL_SYNSET_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/wordnet/hyponyms16.txt";
    public static final String SMALLER_SYNSET_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/wordnet/synsets11.txt";
    public static final String SMALLER_HYPONYM_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/wordnet/hyponyms11.txt";
    public static final String BIG_SYNSET_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/wordnet/synsets1000-subgraph.txt";
    public static final String BIG_HYPONYM_FILE = "/Users/neo/Desktop/CSE/CS47B/proj2/proj2b/data/wordnet/hyponyms1000-subgraph.txt";

    @Test
    public void testActK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = List.of("act");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[act, action, change, demotion, human_action, human_activity, variation]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testChangeK0() {
        WordNetMap wordNetMap = new WordNetMap(SMALL_HYPONYM_FILE, SMALL_SYNSET_FILE);

        String actual = wordNetMap.getHyponyms(Arrays.asList("change")).toString();
        String expected = "[alteration, change, demotion, increase, jump, leap, modification, saltation, transition, variation]";
        assertThat(actual).isEqualTo(expected);
        
        WordNetMap wordNetMapSmaller = new WordNetMap(SMALLER_HYPONYM_FILE, SMALLER_SYNSET_FILE);

        String actualSmaller = wordNetMapSmaller.getHyponyms(Arrays.asList("change")).toString();
        String expectedSmaller = "[change, demotion]";
        assertThat(actualSmaller).isEqualTo(expectedSmaller);
    }

    @Test
    public void testLeafHyponymK0() {
        WordNetMap wordNetMap = new WordNetMap(SMALL_HYPONYM_FILE, SMALL_SYNSET_FILE);
        
        assertThat(wordNetMap.getHyponyms(Arrays.asList("demotion")).toString()).isEqualTo("[demotion]");
        assertThat(wordNetMap.getHyponyms(Arrays.asList("variation")).toString()).isEqualTo("[variation]");
        assertThat(wordNetMap.getHyponyms(Arrays.asList("mutation")).toString()).isEqualTo("[mutation]");
        assertThat(wordNetMap.getHyponyms(Arrays.asList("saltation")).toString()).isEqualTo("[jump, leap, saltation]");
        assertThat(wordNetMap.getHyponyms(Arrays.asList("jump")).toString()).isEqualTo("[jump, leap, saltation]");
        assertThat(wordNetMap.getHyponyms(Arrays.asList("conversion")).toString()).isEqualTo("[conversion]");
        assertThat(wordNetMap.getHyponyms(Arrays.asList("leap")).toString()).isEqualTo("[jump, leap, saltation]");
        assertThat(wordNetMap.getHyponyms(Arrays.asList("increase")).toString()).isEqualTo("[increase]");
    }
    
    @Test
    public void testHalfTreeHyponymK0() {
        WordNetMap wordNetMap = new WordNetMap(SMALL_HYPONYM_FILE, SMALL_SYNSET_FILE);
        
        assertEquals("[act, action, change, demotion, human_action, human_activity, variation]",
            wordNetMap.getHyponyms(Arrays.asList("act")).toString());
        assertEquals("[adjustment, alteration, change, conversion, increase, jump, leap, modification, mutation, saltation, transition]",
            wordNetMap.getHyponyms(Arrays.asList("alteration")).toString());
        assertEquals("[flashback, jump, leap, saltation, transition]",
            wordNetMap.getHyponyms(Arrays.asList("transition")).toString());
    }
    
    @Test
    void testBigFileK0() {
        WordNetMap wordNetMap = new WordNetMap(BIG_HYPONYM_FILE, BIG_SYNSET_FILE);
        assertEquals("[abducens, abducens_nerve, abducent, abducent_nerve, nervus_abducens, sixth_cranial_nerve]",
                wordNetMap.getHyponyms(Arrays.asList("abducent")).toString());
        assertTrue(wordNetMap.getHyponyms(Arrays.asList("antitoxin")).toString().contains("antivenene"));
        assertTrue(wordNetMap.getHyponyms(Arrays.asList("antitoxin")).toString().contains("antivenin"));
        assertTrue(wordNetMap.getHyponyms(Arrays.asList("antitoxin")).toString().contains("tetanus_antitoxin"));
        assertTrue(wordNetMap.getHyponyms(Arrays.asList("nerve_pathway")).toString().contains("cerebral_peduncle"));
        assertTrue(wordNetMap.getHyponyms(Arrays.asList("nerve_pathway")).toString().contains("radiatio_optica"));
        assertTrue(wordNetMap.getHyponyms(Arrays.asList("tract")).toString().contains("cerebral_peduncle"));
        assertTrue(wordNetMap.getHyponyms(Arrays.asList("tract")).toString().contains("radiatio_optica"));
    }
    
    @Test
    void testGibberishK0() {
        WordNetMap wordNetMap = new WordNetMap(SMALL_HYPONYM_FILE, SMALL_SYNSET_FILE);
        assertEquals("[]", wordNetMap.getHyponyms(Arrays.asList("gibberish")).toString());
    }
}
