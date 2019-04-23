package logic.algorithm;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.fest.assertions.Assertions.assertThat;

public class KnuthAlgorithmUnitTest {

    private  KnuthAlgorithm knuthAlgorithm;

    @Before
    public void init() {
        knuthAlgorithm = new KnuthAlgorithm();
    }

    @Test
    @DisplayName("Test metody porównującej")
    public void shouldCompareWithKey() {
        //given
        String key = "1234";
        String input = "1243";

        //where
        String s = knuthAlgorithm.compareFunction(input, key);

        //then
        assertThat(s).isEqualTo("BBWW");
    }
}
