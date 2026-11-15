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

    @Test
    void runOfTwoAppendsCounts() {
        // Jake
    }

    @Test
    void runOfNineUsesOneDigit() {
        // Jake
    }

    @Test
    void runOfTenUsesTwoDigit() {
        // Jake
    }

    @Test
    void mutlipleRepeatedRunsHaveIndependentCounts() {
        // Jake
    }

    @Test
    void digitCharactersCompressCorrect() {
        // Jake
    }

    @Test
    void whitespaceCharactersCompressCorrect() {
        // Jake
    }

    @Test
    void punctuationCharactersCompressCorrect() {
        // Jake
    }

    @Test
    void uppercaseAndLowercaseRemainDistinct() {
        // Jake
    }

    @Test
    void nonAsciiCharactersCompressCorrect() {
        // Jake
    }

    @Test
    void compressLeavesInputUnchanged() {
        // Jake
    }
}
