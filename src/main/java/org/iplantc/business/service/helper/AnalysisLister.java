package org.iplantc.business.service.helper;

import java.util.List;
import net.sf.json.JSONObject;
import org.iplantc.business.service.client.AnalysisListings;
import org.iplantc.business.service.decorator.analysis.AnalysisDecorator;
import org.iplantc.business.service.decorator.analysis.AnalysisDecoratorRetriever;
import org.iplantc.workflow.dao.DaoFactory;

/**
 * Lists analyses; this is the class that implements the bulk of the analysis listing service.
 * 
 * @author Dennis Roberts
 */
public class AnalysisLister {

    /**
     * Used to obtain the analysis listings.
     */
    private final AnalysisListings analysisListings;
    
    /**
     * Used to obtain analysis decorators.
     */
    private final AnalysisDecoratorRetriever decoratorRetriever;

    /**
     * @param analysisListings used to obtain analysis listings.
     * @param decoratorRetriever used to retrieve analysis decorators.
     */
    public AnalysisLister(AnalysisListings analysisListings, AnalysisDecoratorRetriever decoratorRetriever) {
        this.analysisListings = analysisListings;
        this.decoratorRetriever = decoratorRetriever;
    }

    /**
     * Lists analyses in a group and applies the decorators with the given names.
     * 
     * @param analysisGroupId the analysis group identifier.
     * @param decoratorNames the list of decorator names.
     * @param daoFactory used to obtain data access objects.
     * @return a JSON object representing the decorated list of analyses.
     */
    public JSONObject listAnalysesInGroup(String analysisGroupId, List<String> decoratorNames, DaoFactory daoFactory) {
        JSONObject json = analysisListings.getAnalysesInGroup(analysisGroupId);
        decorateAnalysisListings(json, decoratorNames, daoFactory);
        return json;
    }

    /**
     * Decorates analysis listings.
     * 
     * @param json a JSON object representing the analysis listing.
     * @param decoratorNames the list of decorator names.
     * @param daoFactory used to obtain data access objects.
     */
    private void decorateAnalysisListings(JSONObject json, List<String> decoratorNames, DaoFactory daoFactory) {
        for (String decoratorName : decoratorNames) {
            AnalysisDecorator decorator = decoratorRetriever.getDecorator(decoratorName);
            decorator.decorate(daoFactory, json);
        }
    }
}
