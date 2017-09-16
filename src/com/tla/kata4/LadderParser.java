package com.tla.kata4;

/**
 * An interface to parse a String of football ladder data and collate results.
 * Instances of this class are assumed to be stateful; they store parsed information.
 * Clients can call {@link #reset() reset()} to clear the stored data.
 */
public interface LadderParser {

    /**
     * Reset any internal state back to default values, ie:
     * clear any current parsing results.
     */
    void reset();

    /**
     * Parse a String and store state within the Parser instance.
     * 
     * @param line String to parse
     * @throws NumberFormatException if the line contains invalid characters
     * @throws NullPointerException (unchecked) if 'line' is null
     */
    void parse(String line) throws Exception;

    /**
     * Return the current parsing results as a String.
     * 
     * @return the current parsing results as a String.
     */
    String getResult();

}
