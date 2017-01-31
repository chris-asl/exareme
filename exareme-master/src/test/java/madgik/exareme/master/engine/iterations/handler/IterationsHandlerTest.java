package madgik.exareme.master.engine.iterations.handler;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.io.IOException;
import java.util.Map;

import madgik.exareme.master.engine.AdpDBManagerLocator;
import madgik.exareme.master.engine.iterations.IterationsTestGenericUtils;
import madgik.exareme.master.engine.iterations.state.IterationsStateManager;
import madgik.exareme.master.engine.iterations.state.IterationsStateManagerImpl;
import madgik.exareme.master.engine.iterations.state.IterativeAlgorithmState;
import madgik.exareme.master.queryProcessor.composer.AlgorithmsProperties;

/**
 * Functional testing of IterationsHandler
 *
 * @author Christos Aslanoglou <br> caslanoglou@di.uoa.gr <br> University of Athens / Department of
 *         Informatics and Telecommunications.
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class IterationsHandlerTest {
    private static final Logger log = Logger.getLogger(IterationsHandlerTest.class);

    private static final String algorithmName = "DUMMY_ITERATIVE";
    private AlgorithmsProperties.AlgorithmProperties algorithmProperties;
    private IterationsHandler handler;
    private IterationsStateManager stateManager;


    @Before public void SetUp() throws IOException {
        handler = IterationsHandler.getInstance();
        stateManager = IterationsStateManagerImpl.getInstance();

        algorithmProperties = AlgorithmsProperties.AlgorithmProperties.createAlgorithmProperties(
            IterationsTestGenericUtils.prepareParameterProperties(
                    algorithmName, "false", "2"));
    }

    // Functional tests -------------------------------------------------------------------------
    @Test public void ensureIterativeAlgorithmIsSubmittedToStateManager() {
        handler.handleNewIterativeAlgorithmRequest(
                AdpDBManagerLocator.getDBManager(), algorithmProperties);

        final Map<String, IterativeAlgorithmState> iterativeAlgorithmMapping =
                Whitebox.getInternalState(stateManager, "iterativeAlgorithmMapping");

        TestCase.assertFalse(iterativeAlgorithmMapping.isEmpty());
    }
}