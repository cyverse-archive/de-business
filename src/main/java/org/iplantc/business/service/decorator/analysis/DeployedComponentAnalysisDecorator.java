package org.iplantc.business.service.decorator.analysis;

import org.iplantc.business.service.client.DeployedComponentRetriever;
import org.iplantc.business.service.client.impl.DeployedComponentRetrieverImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.iplantc.business.service.decorator.Decorator;
import org.iplantc.workflow.dao.DaoFactory;

/**
 * Used to decorate analysis listings with deployed component information.
 * 
 * @author Dennis Roberts
 */
public class DeployedComponentAnalysisDecorator implements Decorator<JSONObject> {

    /**
     * Used to retrieve the list of deployed components for the analysis.
     */
    private DeployedComponentRetriever retriever;

    /**
     * @param retriever used to retrieve the list of deployed components for the analysis.
     */
    public DeployedComponentAnalysisDecorator(DeployedComponentRetriever retriever) {
        this.retriever = retriever;
    }

    /**
     * Generates the default deployed component analysis decorator.
     * 
     * @param daoFactory used to obtain data access objects.
     * @return the new decorator.
     */
    public static DeployedComponentAnalysisDecorator defaultDecorator(DaoFactory daoFactory) {
        DeployedComponentRetriever retriever = new DeployedComponentRetrieverImpl(daoFactory);
        return new DeployedComponentAnalysisDecorator(retriever);
    }

    /**
     * Adds the list of deployed components to an analysis listing.
     * 
     * @param listing the analysis listing.
     */
    @Override
    public void decorate(JSONObject listing) {
        JSONArray analyses = listing.getJSONArray("templates");
        for (int i = 0; i < analyses.size(); i++) {
            JSONObject analysis = analyses.getJSONObject(i);
            JSONObject components = retriever.fetch(analysis.getString("id"));
            analysis.put("deployed_components", components.getJSONArray("deployed_components"));
        }
    }
}
