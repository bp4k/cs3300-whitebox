package com.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Whitebox branch coverage tests for {@link StringCompressor#compress(char[])}.
 *
 * Decisions under test:
 *   D1: if (chars == null || chars.length == 0)
 *   D2: while (i < chars.length)                              (outer loop)
 *   D3: while (i < chars.length && chars[i] == currentChar)   (inner loop)
 *   D4: if (count > 1)
 *
 * NOTE: The null-input case is intentionally NOT tested here. D1 is a compound
 * condition (null || empty), and exercising null vs. empty separately is
 * condition coverage, which is handled by a separate test suite. Only the
 * empty array is used here to drive D1 true.
 */
class StringCompressorBranchTest {
    private final StringCompressor compressor = new StringCompressor();

    private static char[] repeat(char c, int n) {
        char[] result = new char[n];
        Arrays.fill(result, c);
        return result;
    }

    @Test
    void emptyArrayTakesEarlyReturn() {
        // D1-true
        assertArrayEquals(new char[0], compressor.compress(new char[0]));
    }

    @Test
    void singleCharacterSkipsCount() {
        // D1-false, D2-true, D2-false, D3-true, D3-false, D4-false
        assertArrayEquals(new char[] {'a'}, compressor.compress(new char[] {'a'}));
    }

    @Test
    void runOfTwoIsSmallestCompressibleRun() {
        // D4-true at the boundary count == 2 (just above the count > 1 threshold)
        assertArrayEquals(new char[] {'a', '2'}, compressor.compress(new char[] {'a', 'a'}));
    }

    @Test
    void twoSingletonsEndRunOnMismatch() {
        // D3 exits because the next char differs (not array end); D4-false twice
        assertArrayEquals(new char[] {'a', 'b'}, compressor.compress(new char[] {'a', 'b'}));
    }

    @Test
    void runThenSingleton() {
        // D4 true then false; outer loop re-enters after a compressed run
        assertArrayEquals(new char[] {'a', '2', 'b'},
                compressor.compress(new char[] {'a', 'a', 'b'}));
    }

    @Test
    void singletonThenRun() {
        // D4 false then true; final run ends at array end so D2 exits right after it
        assertArrayEquals(new char[] {'a', 'b', '2'},
                compressor.compress(new char[] {'a', 'b', 'b'}));
    }

    @Test
    void nonAdjacentRunsOfSameCharAreNotMerged() {
        // Count and currentChar are reset per outer iteration
        assertArrayEquals(new char[] {'a', '2', 'b', 'a', '2'},
                compressor.compress(new char[] {'a', 'a', 'b', 'a', 'a'}));
    }

    @Test
    void runOfTenUsesTwoDigitCount() {
        // Digit-width transition in append(int)
        assertArrayEquals(new char[] {'a', '1', '0'}, compressor.compress(repeat('a', 10)));
    }

    @Test
    void runOfOneHundredUsesThreeDigitCount() {
        // Output is not capped at two digits
        assertArrayEquals(new char[] {'z', '1', '0', '0'}, compressor.compress(repeat('z', 100)));
    }

    @Test
    void allDistinctCharactersAreUnchanged() {
        // D4-false on every outer iteration; output length equals input length
        assertArrayEquals(new char[] {'a', 'b', 'c', 'd'},
                compressor.compress(new char[] {'a', 'b', 'c', 'd'}));
    }

    @Test
    void wholeArrayIsOneRun() {
        // D3-true repeated to the array end; D2 exits after a single outer iteration
        assertArrayEquals(new char[] {'x', '7'}, compressor.compress(repeat('x', 7)));
    }
}
