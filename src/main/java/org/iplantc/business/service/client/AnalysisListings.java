package org.iplantc.business.service.client;

import net.sf.json.JSONObject;

/**
 * Used to obtain analysis listings.
 * 
 * @author Dennis Roberts
 */
public interface AnalysisListings {

    /**
     * Gets the list of analyses in an analysis group.
     * 
     * @param analysisGroupId the analysis group identifier.
     * @return a JSON object representing the analysis listing.
     */
    public JSONObject getAnalysesInGroup(String analysisGroupId);
}
