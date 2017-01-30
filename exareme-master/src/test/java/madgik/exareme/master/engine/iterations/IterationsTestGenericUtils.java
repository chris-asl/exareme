package madgik.exareme.master.engine.iterations;

import java.util.HashMap;

import madgik.exareme.master.engine.iterations.handler.IterationsHandlerConstants;
import madgik.exareme.master.queryProcessor.composer.ComposerConstants;

/**
 * Wrapper class which encloses common methods among tests
 *
 * @author Christos Aslanoglou <br> caslanoglou@di.uoa.gr <br> University of Athens / Department of
 *         Informatics and Telecommunications.
 */
public class IterationsTestGenericUtils {

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
}