package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class StringCompressorManualTest {
    
    private final StringCompressor compressor = new StringCompressor();

    @Test
    void nullInputReturnsEmptyArray() {
        // James
        char[] input = null;
        char[] expected = new char[0];
        char[] actual = compressor.compress(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void emptyInputReturnsEmptyArray() {
        // James
        char[] input = new char[0];
        char[] expected = new char[0];
        char[] actual = compressor.compress(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void singleCharacterHasNoCount() {
        // James
        char[] input = {'j'};
        char[] expected = {'j'};
        char[] actual = compressor.compress(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void distinctCharactersRemainUnchanged() {
        // James
        char[] input = {'j', 'a', 'm', 'e', 's'};
        char[] expected = {'j', 'a', 'm', 'e', 's'};
        char[] actual = compressor.compress(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void repeatedRunEndsAtArrayEnd() {
        // James
        char[] input = {'j', 'j', 'j', 'j'};
        char[] expected = {'j', '4'};
        char[] actual = compressor.compress(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void repeatedEndsAtCharacterDifferent() {
        // James
        char[] input = {'j', 'j', 'a', 'a'};
        char[] expected = {'j', '2', 'a', '2'};
        char[] actual = compressor.compress(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void singleFollowedByRepeated() {
        // James
        char[] input = {'j', 'a', 'a', 'a'};
        char[] expected = {'j', 'a', '3'};
        char[] actual = compressor.compress(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void repeatedFollowedBySingle() {
        // James
        char[] input = {'j', 'j', 'j', 'a'};
        char[] expected = {'j', '3', 'a'};
        char[] actual = compressor.compress(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void separatedSameCharacterStaySeparate() {
        // James
        char[] input = {'j', 'j', 'a', 'a', 'j', 'j'};
        char[] expected = {'j', '2', 'a', '2', 'j', '2'};
        char[] actual = compressor.compress(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void mixedSinglesAndRepeatedCorrect() {
        // James
        char[] input = {'j', 'a', 'a', 'm', 'e', 'e', 'e', 's'};
        char[] expected = {'j', 'a', '2', 'm', 'e', '3', 's'};
        char[] actual = compressor.compress(input);
        assertArrayEquals(expected, actual);
    }

    // Shuo Zhang's test inputs
    @Test
    void separatedSRunCountsOnlyTrailingMatches() {
        char[] input = "szsss".toCharArray();
        char[] expected = "szs3".toCharArray();
        assertArrayEquals(expected, compressor.compress(input));
    }

    @Test
    void leadingRunStopsAtDifferentCharacter() {
        char[] input = "ssssz".toCharArray();
        char[] expected = "s4z".toCharArray();
        assertArrayEquals(expected, compressor.compress(input));
    }

    @Test
    void trailingRunEndsAtArrayBoundary() {
        char[] input = "szzzz".toCharArray();
        char[] expected = "sz4".toCharArray();
        assertArrayEquals(expected, compressor.compress(input));
    }

    @Test
    void fourDistinctLettersRetainOrder() {
        char[] input = "shuo".toCharArray();
        char[] expected = "shuo".toCharArray();
        assertArrayEquals(expected, compressor.compress(input));
    }

    @Test
    void alternatingLettersStaySeparate() {
        char[] input = "szsz".toCharArray();
        char[] expected = "szsz".toCharArray();
        assertArrayEquals(expected, compressor.compress(input));
    }

    @Test
    void nullInputProducesZeroLengthArray() {
        char[] input = null;
        char[] expected = new char[0];
        assertArrayEquals(expected, compressor.compress(input));
    }

    @Test
    void singleSRemainsUncounted() {
        char[] input = "s".toCharArray();
        char[] expected = "s".toCharArray();
        assertArrayEquals(expected, compressor.compress(input));
    }

    @Test
    void twoDifferentLettersRetainOrder() {
        char[] input = "sz".toCharArray();
        char[] expected = "sz".toCharArray();
        assertArrayEquals(expected, compressor.compress(input));
    }

    @Test
    void multiplePairsInNameAreCountedIndependently() {
        char[] input = "shuuoozhaang".toCharArray();
        char[] expected = "shu2o2zha2ng".toCharArray();
        assertArrayEquals(expected, compressor.compress(input));
    }

    @Test
    void nonAdjacentHCharactersStaySeparate() {
        char[] input = "shuozhang".toCharArray();
        char[] expected = "shuozhang".toCharArray();
        assertArrayEquals(expected, compressor.compress(input));
    }
}
