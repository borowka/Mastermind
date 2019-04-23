package logic.algorithm.util;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.fest.assertions.Assertions.assertThat;

public class StringUtilsUnitTest {

    @Test
    @DisplayName("Test zamiany tablicy na Stringa")
    public void shouldConvertArrayToText() {
        //given
        int[] arr = {1,2,3,4};

        //where
        String s = StringUtils.textFromArray(arr);

        //then
        assertThat(s).isEqualTo("1234");
    }

    @Test
    @DisplayName("Test wyświetlania n elementów")
    public void shouldDisplayNCharacters() {
        //given
        String a = "a";

        //where
        String s = StringUtils.repeatChar(a,4);

        //then
        assertThat(s).isEqualTo("aaaa");
    }


}
