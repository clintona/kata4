/* $Id: $ */

package com.tla.kata4;

import junit.framework.TestCase;

/**
 * JUnit tests for the LadderParser class.
 *
 * @author Clinton Arnall
 */
public class LadderParserTestCase extends TestCase {

    /** Creates a new instance of LadderParserTestCase */
    public LadderParserTestCase(String testName) {
        super(testName);
    }


    public void testConstructor() {
        GoalDifferenceParser p = new GoalDifferenceParser();
        assertNull(p.getLowestGoalDifferenceTeam());
    }


    public void testParseSuccess() throws Exception {
        GoalDifferenceParser p = new GoalDifferenceParser();
        p.parse("    1. Arsenal         38    26   9   3    79  -  36    87");
        String team = p.getLowestGoalDifferenceTeam();
        assertNotNull("Team name is null - regexp didn't match", team);
        assertEquals("Arsenal", team.trim());
    }


    public void testParseIgnore() throws Exception {
        GoalDifferenceParser p = new GoalDifferenceParser();
        // this line should be ignored
        p.parse("    x. Arsenal         38    26   9   3    79  -  36    87");
        assertNull(p.getLowestGoalDifferenceTeam());
    }


    public void testParseException() throws Exception {
        GoalDifferenceParser p = new GoalDifferenceParser();
        // this line should fail to parse b/c Goals For is text
        try {
            p.parse("  1. Arsenal         xx    26   9   3    xx  -  36    87");
            fail("Expected exception");
        } catch (NumberFormatException e) {
            // expected, ignored
        }
        assertNull(p.getLowestGoalDifferenceTeam());

    }


    public void testReset() throws Exception {
        GoalDifferenceParser p = new GoalDifferenceParser();
        p.parse("    1. Arsenal         38    26   9   3    79  -  36    87");
        assertEquals("Arsenal", p.getLowestGoalDifferenceTeam().trim());
        p.reset();
        assertNull(p.getLowestGoalDifferenceTeam());
    }
}
