package com.example;

import org.junit.jupiter.api.Test;

public class StringCompressorManualTest {
    
    private final StringCompressor compressor = new StringCompressor();

    @Test
    void nullInputReturnsEmptyArray() {
        // James
    }

    @Test
    void emptyInputReturnsEmptyArray() {
        // James
    }

    @Test
    void singleCharacterHasNoCount() {
        // James
    }

    @Test
    void distinctCharactersRemainUnchanged() {
        // James
    }

    @Test
    void repeatedRunEndsAtArrayBound() {
        // James
    }

    @Test
    void repeatedRunEndsAtCharacterMissmatch() {
        // James
    }

    @Test
    void singletonFollowedByRepeatedRun() {
        // James
    }

    @Test
    void repeatedRunFollowedBySingleton() {
        // James
    }

    @Test
    void separatedRunsOfSameCharacterStaySeparate() {
        // James
    }

    @Test
    void mixedSingletonsAndRepeatedRunsCorrect() {
        // James
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
