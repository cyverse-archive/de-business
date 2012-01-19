package org.iplantc.business.service.client.mock;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import org.iplantc.business.service.client.PipelineAnalysisValidator;

/**
 * A mock pipeline analysis validation service.
 * 
 * @author Dennis Roberts
 */
public class MockPipelineAnalysisValidator implements PipelineAnalysisValidator {

    /**
     * Maps analysis identifiers to pipeline eligibility information.
     */
    private Map<String, JSONObject> validationMap = new HashMap<String, JSONObject>();

    /**
     * Adds validation information to the internal map.
     * 
     * @param analysisId the analysis identifier.
     * @param validationInformation the validation information to add.
     */
    public void addValidationInformation(String analysisId, JSONObject validationInformation) {
        validationMap.put(analysisId, validationInformation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JSONObject fetch(String analysisId) {
        return validationMap.get(analysisId);
    }
}
