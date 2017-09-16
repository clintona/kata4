/* $Id: $ */

package com.tla.kata4;

import junit.framework.TestCase;

/**
 * JUnit tests for the LadderManager class.
 *
 * @author Clinton Arnall
 */
public class LadderManagerTestCase extends TestCase {

    /** Creates a new instance of LadderManagerTestCase */
    public LadderManagerTestCase(String testName) {
        super(testName);
    }

    public void testConstructor() {
        LadderManager p = new LadderManager();
        assertTrue(p.getParser() instanceof GoalDifferenceParser);
    }

    public void testSetParser() {
        // success case
        LadderManager p = new LadderManager();
        GoalDifferenceParser myParser = new GoalDifferenceParser();
        p.setParser(myParser);
        assertEquals(myParser, p.getParser());

        // failure case
        try {
            myParser = null;
            p.setParser(myParser);
            fail("Expected exception");
        } catch (NullPointerException expected) {
            // expected, ignored
        }
    }

    // Apologies, this test fails under Eclipse, but works under ant.
    // Class/File path issue.
    public void testParseFileSuccess() throws Exception {
        LadderManager p = new LadderManager();
        // the test classpath sets build/test dir as the current dir
        p.parseFile("../../football.dat");
        String team = p.getParser().getResult();
        assertEquals("Aston_Villa", team);
    }

    public void testParseFileNull() throws Exception {
        LadderManager p = new LadderManager();
        try {
            p.parseFile(null);
            fail("Expected exception");
        } catch (Exception expected) {
            // expected, ignored
        }
    }

    public void testParseFileNotFound() throws Exception {
        LadderManager p = new LadderManager();
        try {
            p.parseFile("/path.to/bogus.dir/%^$&^%$131.dat");
            fail("Expected exception");
        } catch (Exception expected) {
            // expected, ignored
        }
    }

}
