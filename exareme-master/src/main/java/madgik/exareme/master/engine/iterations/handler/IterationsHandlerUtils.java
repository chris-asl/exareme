package madgik.exareme.master.engine.iterations.handler;

import org.apache.log4j.Logger;

import madgik.exareme.master.queryProcessor.composer.AlgorithmsProperties;

import static madgik.exareme.master.queryProcessor.composer.ComposerConstants.mipAlgorithmsDemoWorkingDirectory;

/**
 * @author Christos Aslanoglou <br> caslanoglou@di.uoa.gr <br> University of Athens / Department of
 *         Informatics and Telecommunications.
 */
public class IterationsHandlerUtils {
    private static final Logger log = Logger.getLogger(IterationsHandler.class);

    /**
     * Generates a unique algorithm key.
     * <p>
     * It provides a unique name with <b>granularity of a ms!</b>
     *
     * @param algorithmProperties The algorithm's properties object.
     * @return A unique algorithm key.
     */
    static String generateAlgorithmRequestKey(AlgorithmsProperties.AlgorithmProperties algorithmProperties) {
        return "request_" +
                algorithmProperties.getName() +
                "_" +
                String.valueOf(System.currentTimeMillis());
    }

    /**
     * Generates the iterationsDB absolute filename.
     *
     * @param algorithmKey The algorithm key generated for the current iterative algorithm.
     * @return The absolute filename of iterationsDB for this algorithm.
     */
    public static String generateIterationsDBName(String algorithmKey) {
        return mipAlgorithmsDemoWorkingDirectory
                + algorithmKey + "/"
                + IterationsHandlerConstants.iterationsParameterIterDBValueSuffix;
    }
}