package org.iplantc.business.service.client;

import net.sf.json.JSONObject;
import org.iplantc.business.service.decorator.DecorationRetriever;

/**
 * Used to validate analyses for use in a pipeline.
 * 
 * @author Dennis Roberts
 */
public interface PipelineAnalysisValidator extends DecorationRetriever<String, JSONObject> {

    /**
     * Used to validate an analysis for pipelines.
     * 
     * @param analysisId the analysis identifier.
     * @return a JSON object indicating whether or not the analysis can be used in pipelines.
     */
    @Override
    public JSONObject fetch(String analysisId);
}
