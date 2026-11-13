package com.example;

public class StringCompressor {
    
    /**
     * Compresses an array of characters by summarizing consecutive duplicate characters with counts.
     * 
     * @param chars The input character array
     * @return A new compressed character array
     */
    public char[] compress(char[] chars) {
        // Edge case: null or empty array
        if (chars == null || chars.length == 0) {
            return new char[0];
        }

        StringBuilder compressedString = new StringBuilder();
        int i = 0;

        while (i < chars.length) {
            char currentChar = chars[i];
            int count = 0;

            // Count consecutive occurrences of the current character
            while (i < chars.length && chars[i] == currentChar) {
                count++;
                i++;
            }

            // Append the character to the result
            compressedString.append(currentChar);

            // Append the count if greater than 1
            if (count > 1) {
                // If count is >= 10, this automatically handles multiple digits
                compressedString.append(count);
            }
        }

        return compressedString.toString().toCharArray();
    }
}