// $Id:$
package com.tla.kata4;

/**
 * A Class to parse a String of football ladder data and collate results.
 * Instances of this class are stateful; they store parsed information.
 * Clients can call {@link #reset() reset()} to clear the stored data.
 */
public class GoalDifferenceParser implements LadderParser {

    /** The current team with the lowest goal difference, null if unset. */
    private String lowestGoalDifferenceTeam;

    /** The current lowest goal difference, Integer.MAX_VALUE if unset. */
    private int lowestGoalDifference;

    /** Default constructor. */
    public GoalDifferenceParser() {
        reset();
    }

    /**
     * Reset internal state back to default values, ie:
     * clear any current parsing results.
     */
    public void reset() {
        this.lowestGoalDifferenceTeam = null;
        this.lowestGoalDifference = Integer.MAX_VALUE;
    }

    /**
     * Parse a String of ladder information, extracting the team name and it's
     * for/against goals. Non compliant lines are ignored and will not effect
     * the state of this parser.
     * The {@link #parse(String) parse(String)} method will first split the
     * line into all tokens separated by spaces. Only lines beginning with
     * a digit followed by a period, eg; "1." will be parsed.
     * 
     * @param line String to parse
     * @throws NumberFormatException if the line contains invalid characters
     * @throws NullPointerException (unchecked) if 'line' is null
     */
    public void parse(String line) throws Exception {
        // parse line into tokens (whitespace delimiters)
        String[] tokens = line.trim().split("\\s+");

        // the only lines we are interested in contain 10 tokens
        // and start with digits [0-9] followed by a period [.]
        if (tokens.length == 10 && tokens[0].matches("^[0-9]*\\.")) {
            int goalsFor = Integer.parseInt(tokens[6]);
            int goalsAgainst = Integer.parseInt(tokens[8]);
            int goalDelta = Math.abs(goalsFor - goalsAgainst);
            if (goalDelta < this.lowestGoalDifference) {
                this.lowestGoalDifference = goalDelta;
                this.lowestGoalDifferenceTeam = tokens[1];
            }
        }
    }

    /**
     * Return the team name with the current lowest <i>absolute</i> goal
     * difference (for vs against) stored by this parser. Callers should ensure
     * all their data has been parsed before calling this method,
     * otherwise results may be incorrect. Note that a call to
     * {@link #reset() reset()} will nullify the current team.
     * 
     * @return the team name with the current lowest goal difference (for vs
     *         against) stored by this parser.
     */
    public String getLowestGoalDifferenceTeam() {
        return this.lowestGoalDifferenceTeam;
    }

    /**
     * Return the team name with the current lowest <i>absolute</i> goal
     * difference (for vs against) stored by this parser. Callers should ensure
     * all their data has been parsed before calling this method,
     * otherwise results may be incorrect. Note that a call to
     * {@link #reset() reset()} will nullify the current team.
     * 
     * @return the team name with the current lowest goal difference (for vs against) stored by this
     *         parser.
     */
    public String getResult() {
        return getLowestGoalDifferenceTeam();
    }
}
