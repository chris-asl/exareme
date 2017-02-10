package madgik.exareme.master.engine.iterations;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import madgik.exareme.common.consts.HBPConstants;
import madgik.exareme.master.engine.iterations.handler.IterationsHandlerConstants;
import madgik.exareme.master.queryProcessor.composer.Composer;
import madgik.exareme.master.queryProcessor.composer.ComposerConstants;

/**
 * Wrapper class which encloses common methods among tests
 *
 * @author Christos Aslanoglou <br> caslanoglou@di.uoa.gr <br> University of Athens / Department of
 *         Informatics and Telecommunications.
 */
public class IterationsTestGenericUtils {

    public static final String ALGORITHMS_DEV_DIRECTORY
            = "src/test/resources/madgik/exareme-tools/algorithms-dev";

    /**
     * Generate dummy algorithm parameters to simulate request from gateway.
     * @param algorithmName the algorithm key that was generated
     * @param iterationsPropertyConditionQueryProvided whether a condition query is provided
     *                                                 (according to properties file of
     *                                                 algorithm), should be true/false String
     * @param iterationsMaximumNumber the maximum iterations number of the algorithm (according
     *                                to properties file), should be a number
     * @return the algorithm properties (usually generated by the gateway handler)
     */
    public static HashMap<String, String> prepareParameterProperties(
            String algorithmName, String iterationsPropertyConditionQueryProvided,
            String iterationsMaximumNumber) {
        HashMap<String, String> inputContent = new HashMap<String, String>();
        if (iterationsPropertyConditionQueryProvided != null)
            inputContent.put(
                    IterationsHandlerConstants.iterationsPropertyConditionQueryProvided,
                    iterationsPropertyConditionQueryProvided);
        if (iterationsMaximumNumber != null)
            inputContent.put(
                    IterationsHandlerConstants.iterationsPropertyMaximumNumber,
                    iterationsMaximumNumber);
        inputContent.put(ComposerConstants.algorithmKey, algorithmName);
        inputContent.put(ComposerConstants.outputGlobalTblKey, "output");
        return inputContent;
    }

    /**
     * Sets the algorithms repository path of HBPConstants to be the one in src/test/resources
     * @throws IOException if getCanonicalPath fails
     */
    public static void overwriteHBPConstantsRepositoryPath()
            throws Exception {
        // Update HBP.repositoryPath field
        File algorithmsDevDirectory = new File(ALGORITHMS_DEV_DIRECTORY);
        setFinalStatic(
                HBPConstants.class.getDeclaredField("DEMO_ALGORITHMS_WORKING_DIRECTORY"),
                algorithmsDevDirectory.getCanonicalPath());
    }

    /**
     * Sets the algorithms repository path of Composer module to be the one in src/test/resources
     * @throws IOException if getCanonicalPath fails
     */
    public static void overwriteComposerModuleRepositoryPath()
            throws Exception {
        File algorithmsDevDirectory = new File(ALGORITHMS_DEV_DIRECTORY);
        setFinalStatic(
                Composer.class.getDeclaredField("repoPath"),
                algorithmsDevDirectory.getCanonicalPath() + "/");
    }

    /**
     * Sets a final static field - <b>use with caution</b>
     * @see <a href="http://stackoverflow.com/questions/30703149/mock-private-static-final-field-\
     using-mockito-or-jmockit">StackOverflow related answer</a>
     */
    private static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }
}
