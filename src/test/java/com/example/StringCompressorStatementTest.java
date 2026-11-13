package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * AI-generated tests focused on statement execution and output correctness.
 * Null and empty inputs are assumed to return empty arrays, as implemented;
 * no exception is expected for these or any of the valid char[] inputs below.
 */
class StringCompressorStatementTest {
    private final StringCompressor compressor = new StringCompressor();

    @Test
    void nullInputReturnsEmptyArray() {
        // The null check short-circuits length access and executes the early return.
        assertArrayEquals(new char[0], compressor.compress(null));
    }

    @Test
    void emptyInputReturnsEmptyArray() {
        // Evaluates length and executes the early return before initialization.
        assertArrayEquals(new char[0], compressor.compress(new char[0]));
    }

    @Test
    void singleCharacterIsUnchanged() {
        // Executes initialization, both loop bodies once, character append, and return.
        // At the array end, the inner loop's length check prevents an invalid access.
        assertArrayEquals(new char[] {'a'}, compressor.compress(new char[] {'a'}));
    }

    @Test
    void distinctCharactersAreUnchanged() {
        // Repeats the outer body; each character change ends the current inner loop.
        assertArrayEquals(new char[] {'a', 'b', 'c'},
                compressor.compress(new char[] {'a', 'b', 'c'}));
    }

    @Test
    void repeatedRunAppendsCount() {
        // Repeats count++ and i++; count > 1 allows the count append to execute.
        assertArrayEquals(new char[] {'a', '3'},
                compressor.compress(new char[] {'a', 'a', 'a'}));
    }

    @Test
    void multipleRepeatedRunsHaveSeparateCounts() {
        // Executes character and count appends for three successive runs.
        assertArrayEquals(new char[] {'a', '2', 'b', '2', 'c', '3'},
                compressor.compress(new char[] {'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
    }

    @Test
    void separatedRunsOfSameCharacterStaySeparate() {
        // Reinitializes currentChar and count for each run, including the later 'a' run.
        assertArrayEquals(new char[] {'a', '2', 'b', 'a', '2'},
                compressor.compress(new char[] {'a', 'a', 'b', 'a', 'a'}));
    }

    @Test
    void mixedSingletonsAndRepeatedRunsCompressCorrectly() {
        // Executes character appends for every run and count appends for repeated runs.
        assertArrayEquals(new char[] {'a', 'b', '2', 'c', 'd', '3', 'e'},
                compressor.compress(new char[] {'a', 'b', 'b', 'c', 'd', 'd', 'd', 'e'}));
    }

    @Test
    void nineCharacterRunUsesSingleDigitCount() {
        // Executes the counting statements nine times and appends a one-digit count.
        assertArrayEquals(new char[] {'a', '9'},
                compressor.compress(new char[] {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'}));
    }

    @Test
    void tenCharacterRunUsesTwoDigitCount() {
        // Executes the same count append statement with a two-digit value.
        assertArrayEquals(new char[] {'a', '1', '0'},
                compressor.compress(new char[] {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'}));
    }

    @Test
    void digitCharactersAreCompressedAsCharacters() {
        // Executes ordinary run counting and appending with numeric character input.
        assertArrayEquals(new char[] {'1', '3', '2'},
                compressor.compress(new char[] {'1', '1', '1', '2'}));
    }

    @Test
    void whitespaceCharactersArePreservedAndCounted() {
        // Executes the same statements for spaces, tabs, and a singleton newline.
        assertArrayEquals(new char[] {' ', '2', '\t', '2', '\n'},
                compressor.compress(new char[] {' ', ' ', '\t', '\t', '\n'}));
    }

    @Test
    void punctuationCharactersArePreservedAndCounted() {
        // Executes character/count appends with punctuation and a final singleton.
        assertArrayEquals(new char[] {'!', '3', '?', '2', '.'},
                compressor.compress(new char[] {'!', '!', '!', '?', '?', '.'}));
    }
}
