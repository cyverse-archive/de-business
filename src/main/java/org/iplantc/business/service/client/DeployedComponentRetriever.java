package org.iplantc.business.service.client;

import net.sf.json.JSONObject;
import org.iplantc.business.service.decorator.DecorationRetriever;

/**
 * Used to retrieve deployed components for analyses.
 * 
 * @author Dennis Roberts
 */
public interface DeployedComponentRetriever extends DecorationRetriever<String, JSONObject> {

    /**
     * Obtains the list of deployed components in an analysis.
     * 
     * @param analysisId the analysis identifier.
     * @return a JSON object representing the list of deployed components.
     */
    @Override
    public JSONObject fetch(String analysisId);
}
