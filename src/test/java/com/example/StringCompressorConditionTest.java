package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Whitebox condition coverage tests for {@link StringCompressor#compress(char[])}.
 *
 * Branch coverage only requires each decision to evaluate true and false once.
 * Condition coverage additionally requires every atomic condition inside a
 * compound decision to evaluate both true and false. The two compound
 * decisions in compress() are:
 *
 *   D1: if (chars == null || chars.length == 0)
 *       C1: chars == null
 *       C2: chars.length == 0        (only reached when C1 is false)
 *
 *   D3: while (i < chars.length && chars[i] == currentChar)
 *       C3: i < chars.length
 *       C4: chars[i] == currentChar  (only reached when C3 is true)
 *
 * In JaCoCo's bytecode-level model each atomic condition is its own branch,
 * so full condition coverage of C1-C4 (plus the simple decisions D2 and D4,
 * revisited here at their boundaries) is what closes the gap to 12/12
 * branches that the branch-coverage suite intentionally leaves open by
 * never passing null.
 */
class StringCompressorConditionTest {
    private final StringCompressor compressor = new StringCompressor();

    @Test
    void nullInputMakesNullCheckTrue() {
        // C1-true: short-circuits C2 (chars.length would throw on null),
        // proving the || evaluates left-to-right and takes the early return.
        assertArrayEquals(new char[0], compressor.compress(null));
    }

    @Test
    void emptyInputMakesNullCheckFalseAndLengthCheckTrue() {
        // C1-false, C2-true: D1 is true only via the second condition.
        assertArrayEquals(new char[0], compressor.compress(new char[0]));
    }

    @Test
    void nonEmptyInputMakesBothEarlyReturnConditionsFalse() {
        // C1-false, C2-false: D1 is false, so the compression body runs.
        assertArrayEquals(new char[] {'a'}, compressor.compress(new char[] {'a'}));
    }

    @Test
    void innerLoopContinuesWhileBothConditionsHold() {
        // C3-true, C4-true on the second inner iteration: the run keeps
        // extending while both the bounds check and the match check hold.
        assertArrayEquals(new char[] {'a', '2'}, compressor.compress(new char[] {'a', 'a'}));
    }

    @Test
    void innerLoopExitsOnMismatchWhileInBounds() {
        // C3-true, C4-false: the loop ends because chars[i] != currentChar,
        // not because the array ended (a trailing char remains after the run).
        assertArrayEquals(new char[] {'a', '2', 'b'},
                compressor.compress(new char[] {'a', 'a', 'b'}));
    }

    @Test
    void innerLoopExitsAtArrayEndWithoutEvaluatingMatch() {
        // C3-false: at i == chars.length the && short-circuits, so C4 is never
        // evaluated (which would otherwise index out of bounds).
        assertArrayEquals(new char[] {'b', '3'},
                compressor.compress(new char[] {'b', 'b', 'b'}));
    }

    @Test
    void outerLoopRunsOncePerRunThenExits() {
        // D2: true for each of the two runs, then false once i reaches the
        // end; the exit happens immediately after the final run is consumed.
        assertArrayEquals(new char[] {'x', '2', 'y'},
                compressor.compress(new char[] {'x', 'x', 'y'}));
    }

    @Test
    void countOfOneSkipsCountAppendAndCountOfTwoAppendsIt() {
        // D4 at its boundary: count == 1 (false) for 'a', count == 2 (true)
        // for 'b' — both outcomes of count > 1 within one input.
        assertArrayEquals(new char[] {'a', 'b', '2'},
                compressor.compress(new char[] {'a', 'b', 'b'}));
    }
}
