package org.iplantc.business.service.client.mock;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.iplantc.business.service.client.AnalysisListings;

/**
 * A mock analysis listing service.
 * 
 * @author Dennis Roberts
 */
public class MockAnalysisListings implements AnalysisListings {

    /**
     * The analyses in the listing.
     */
    private List<JSONObject> listing = new ArrayList<JSONObject>();

    /**
     * @param analysisId the identifier of the analysis to add.
     */
    public void addAnalysis(String analysisId) {
        listing.add(createAnalysisJson(analysisId));
    }

    /**
     * Creates an analysis to add.
     * 
     * @param analysisId the analysis identifier.
     * @return a JSON object representing the analysis.
     */
    public JSONObject createAnalysisJson(String analysisId) {
        JSONObject json = new JSONObject();
        json.put("id", analysisId);
        return json;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JSONObject getAnalysesInGroup(String analysisGroupId) {
        JSONObject result = new JSONObject();
        result.put("templates", JSONArray.fromObject(listing));
        return result;
    }
}
