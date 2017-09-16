// $Id:$
package com.tla.kata4;

import java.io.FileReader;
import java.io.LineNumberReader;

/**
 * A main class to manage a football ladder file, see Overview section herein.
 *
 * <h2>Overview</h2>
 * A main class for a program to read a football ladder file and "print the
 * name of the team with the smallest difference in for and against goals".
 * See http://codekata.pragprog.com/2007/01/kata_four_data_.html
 *
 * <h2>Design</h2>
 * 2 different design approaches to this problem are considered below.
 *
 * <h3>Scalable Approach</h3>
 * Here, the for/against fields are extracted from each line of the data file,
 * converted to integers and the for/against difference is calculated.
 * This figure is compared to the previous to find the overall lowest.
 * <p/>
 * This approach will scale well for large data files and will compute the
 * answer relatively quickly. However, it's not as easily extended should
 * additional requirements arise. In particular, sorting the data cannot easily
 * be achieved under this approach, because most common sort algorithms require
 * all data to be held in memory (eg: bubblesort, mergesort). Further, any
 * additional data mining operations will require additional data conversion
 * and calculation routines, which will require refactoring.
 *
 * <h3>Extensible Approach</h3>
 * Here, each line of the data file is parsed into a data structure and stored
 * in an in-memory Collection or Array. The file data is converted as it is
 * read, into mostly integer data, except the team names which are Strings.
 * This approach could employ design patterns like a Factory, to build a
 * Collection of Value Objects representing Ladder statistics. A business
 * facade class could then house various data mining methods that use this
 * Ladder Collection, to answer various questions like "what is the team with
 * the lowest for/against difference?".
 * <p/>
 * Execution is expected to be slower than the "scalable" design described
 * above, because of the overhead of Object creation and type conversion for all
 * fields within the data file. The resultant Objects are cached in memory, so
 * this approach will not scale for large data files. However, this approach
 * lends itself to sorting operations and any additional data mining
 * requirements are more easily added as individual methods, without the need
 * for additional parsing.
 *
 * <h2>Design Assumptions</h2>
 * In practice, a competent developer would determine the non-functional
 * requirements to help decide which design approach to follow. Another
 * consideration is the business context in which the software runs, such as
 * the software life expectancy, contractual and support obligations, response
 * times and the target execution platform.
 * <p/>
 * Without knowing the non-functional requirements, I have elected to follow
 * the "scalable" design, because it exactly matches the requirements without
 * imagined future considerations, like sorting. To address any future
 * extensions, I tried to encapsulate and decouple the parsing, to make adding
 * extra functionality a little easier.
 * Edit 2017: I defined a LadderParser interface so that different parsers
 * could be used if needed. It assumes the entire parsing results can be
 * returned as a String.
 * <p/>
 * Note that the website
 * http://codekata.pragprog.com/2007/01/kata_four_data_.html
 * also lists a one line, unix shell script solution
 * that should be a consideration if it's proven to be correct and the target
 * execution platform runs a unix operating system.
 *
 * <h2>Ladder File Format</h2>
 * The ladder file must contain lines formatted as per the sample below:<br/>
 * 
 * <pre>
 *   <code>
 *      Team            P     W    L   D    F      A     Pts
 *   1. Arsenal         38    26   9   3    79  -  36    87
 *   2. Liverpool       38    24   8   6    67  -  30    80
 *   3. Manchester_U    38    24   5   9    87  -  45    77
 *   </code>
 * </pre>
 */
public class LadderManager {

    /** Non-zero error return code. */
    public static final int RET_ERR = 1;

    /**
     * The underlying parser used to parse the football file data.
     * This parser can be injected at runtime, to swap in a different
     * parser should future requirements change, or to swap in a Mock
     * for testing. Clients call the parser to extract parsed information.
     */
    private LadderParser parser;

    /** Default constructor. Create a default LadderParser instance. */
    public LadderManager() {
        this.parser = new GoalDifferenceParser();
    }

    /**
     * Set the LadderParser used to parse the data file in the
     * {@link #parseFile(String) parseFile} method.
     * 
     * @param p LadderParser instance to use, cannot be null
     * @throws NullPointerException (unchecked) if LadderParser instance is null
     */
    public void setParser(LadderParser p) {
        if (p == null) {
            throw new NullPointerException("Null parser instance");
        }
        this.parser = p;
    }

    /**
     * Return the LadderParser instance used by this Manager.
     * This should never be null.
     * 
     * @return the LadderParser instance used by this Manager.
     */
    public LadderParser getParser() {
        return this.parser;
    }

    /**
     * Read and parse a football data file, invoking the underlying Parser
     * to analyse and collate information of interest.
     * On successful execution, parsed data is available from the LadderParser
     * instance, see {@link #getParser() getParser()}.
     *
     * @param path relative or absolute file path
     * @throws Exception if the file is invalid
     */
    public void parseFile(String path) throws Exception {
        LineNumberReader reader = null;
        try {
            reader = new LineNumberReader(new FileReader(path));
            String line = null;
            while ((line = reader.readLine()) != null) {
                this.parser.parse(line);
            }
        } catch (Exception e) {
            // augment any Exception with a line number and filename
            String msg = "";
            if (reader != null) {
                msg = " at line " + reader.getLineNumber();
            }
            msg = " file: " + path + msg;
            throw new Exception(e + msg, e);
        } finally {
            // attempt to deallocate any resources
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * Application entry point.
     * 
     * @param args String array of command line arguments
     * @throws Exception if an error occurs
     */
    public static void main(String[] args) throws Exception {
        // parse command line args
        if (args.length < 1) {
            System.out.println("Usage: LadderParser <filename>");
            System.exit(RET_ERR);
        }

        LadderManager manager = new LadderManager();
        manager.parseFile(args[0]);
        System.out.println(manager.getParser().getResult());

    } // end main

}
