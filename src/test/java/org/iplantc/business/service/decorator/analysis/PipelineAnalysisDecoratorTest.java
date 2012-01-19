package org.iplantc.business.service.decorator.analysis;

import org.iplantc.business.service.decorator.analysis.PipelineAnalysisDecorator;
import net.sf.json.JSONArray;
import org.iplantc.business.service.client.mock.MockAnalysisListings;
import net.sf.json.JSONObject;
import org.iplantc.business.service.client.mock.MockPipelineAnalysisValidator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for org.iplantc.business.service.pipelines.PipelineAnalysisListings.
 * 
 * @author Dennis Roberts
 */
public class PipelineAnalysisDecoratorTest {

    /**
     * The pipeline analysis listings instance to test.
     */
    private PipelineAnalysisDecorator instance;

    /**
     * Used to obtain analysis listings.
     */
    private MockAnalysisListings listings;

    /**
     * Used to retrieve pipeline eligibility information for each analysis.
     */
    private MockPipelineAnalysisValidator validator;

    @Before
    public void initialize() {
        initializeAnalysisListings();
        initializePipelineAnalysisValidator();
        initializePipelineAnalysisListings();
    }

    /**
     * Initializes the mock analysis listings object.
     */
    private void initializeAnalysisListings() {
        listings = new MockAnalysisListings();
        listings.addAnalysis("foo");
        listings.addAnalysis("bar");
        listings.addAnalysis("baz");
    }

    /**
     * Initializes the pipeline analysis validator.
     */
    private void initializePipelineAnalysisValidator() {
        validator = new MockPipelineAnalysisValidator();
        validator.addValidationInformation("foo", createPipelineValidationObject(true, ""));
        validator.addValidationInformation("bar", createPipelineValidationObject(true, ""));
        validator.addValidationInformation("baz", createPipelineValidationObject(false, "multistep"));
    }

    /**
     * Creates a JSON object indicating whether or not an analysis is eligible for a pipeline.
     * 
     * @param valid true if the analysis is eligible to be included in a pipeline.
     * @param reason the reason for ineligibility if the analysis isn't eligible.
     * @return the JSON object.
     */
    private JSONObject createPipelineValidationObject(Boolean valid, String reason) {
        JSONObject json = new JSONObject();
        json.put("is_valid", valid);
        json.put("reason", reason);
        return json;
    }

    /**
     * Initializes the pipeline analysis listings object.
     */
    private void initializePipelineAnalysisListings() {
        instance = new PipelineAnalysisDecorator(validator);
    }

    /**
     * Test of listAnalysesForPipeline method, of class PipelineAnalysisListings.
     */
    @Test
    public void testListAnalysesForPipeline() {
        JSONObject json = listings.getAnalysesInGroup("unused identifier");
        instance.decorate(json);
        assertTrue(json.has("templates"));

        JSONArray analyses = json.getJSONArray("templates");
        assertEquals(3, analyses.size());

        JSONObject analysis1 = analyses.getJSONObject(0);
        assertEquals("foo", analysis1.getString("id"));
        assertTrue(analysis1.has("pipeline_eligibility"));
        assertTrue(analysis1.getJSONObject("pipeline_eligibility").getBoolean("is_valid"));
        assertEquals("", analysis1.getJSONObject("pipeline_eligibility").getString("reason"));

        JSONObject analysis2 = analyses.getJSONObject(1);
        assertEquals("bar", analysis2.getString("id"));
        assertTrue(analysis2.has("pipeline_eligibility"));
        assertTrue(analysis2.getJSONObject("pipeline_eligibility").getBoolean("is_valid"));
        assertEquals("", analysis2.getJSONObject("pipeline_eligibility").getString("reason"));

        JSONObject analysis3 = analyses.getJSONObject(2);
        assertEquals("baz", analysis3.getString("id"));
        assertTrue(analysis3.has("pipeline_eligibility"));
        assertFalse(analysis3.getJSONObject("pipeline_eligibility").getBoolean("is_valid"));
        assertEquals("multistep", analysis3.getJSONObject("pipeline_eligibility").getString("reason"));
    }
}
