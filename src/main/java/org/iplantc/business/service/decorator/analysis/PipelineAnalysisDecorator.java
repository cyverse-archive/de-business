package org.iplantc.business.service.decorator.analysis;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.iplantc.business.service.client.PipelineAnalysisValidator;
import org.iplantc.business.service.client.impl.PipelineAnalysisValidatorImpl;
import org.iplantc.business.service.decorator.Decorator;
import org.iplantc.workflow.dao.DaoFactory;

/**
 * Used to decorate analysis listings with pipeline eligibility information.
 * 
 * @author Dennis Roberts
 */
class PipelineAnalysisDecorator implements Decorator<JSONObject> {

    /**
     * Used to verify that analyses can be used in an analysis.
     */
    private PipelineAnalysisValidator pipelineAnalysisValidator;

    /**
     * @param validator used to determine whether or not an analysis may be used in a pipeline.
     */
    public PipelineAnalysisDecorator(PipelineAnalysisValidator validator) {
        this.pipelineAnalysisValidator = validator;
    }

    /**
     * Creates a default pipeline analysis decorator.
     * 
     * @param daoFactory used to obtain data access objects.
     * @return the decorator.
     */
    public static PipelineAnalysisDecorator defaultPipelineAnalysisDecorator(DaoFactory daoFactory) {
        PipelineAnalysisValidator validator = new PipelineAnalysisValidatorImpl(daoFactory);
        return new PipelineAnalysisDecorator(validator);
    }

    /**
     * Adds pipeline eligibility information to analysis listings.
     * 
     * @param analysisListing the analysis listing to add the information to.
     */
    @Override
    public void decorate(JSONObject analysisListing) {
        JSONArray analyses = analysisListing.getJSONArray("templates");
        for (int i = 0; i < analyses.size(); i++) {
            JSONObject analysis = analyses.getJSONObject(i);
            String analysisId = analysis.getString("id");
            analysis.put("pipeline_eligibility", pipelineAnalysisValidator.fetch(analysisId));
        }
    }
}
