package org.iplantc.business.service.client.impl;

import net.sf.json.JSONObject;
import org.iplantc.business.IPlantBusinessException;
import org.iplantc.business.service.client.PipelineAnalysisValidator;
import org.iplantc.workflow.dao.DaoFactory;
import org.mule.DefaultMuleMessage;
import org.mule.RequestContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;

/**
 * Used to validate analyses for use in a pipeline.
 * 
 * @author Dennis Roberts
 */
public class PipelineAnalysisValidatorImpl extends AbstractMuleClient implements PipelineAnalysisValidator {

    /**
     * Used to obtain data access objects.
     */
    private DaoFactory daoFactory;

    /**
     * @param daoFactory used to obtain data access objects.
     */
    public PipelineAnalysisValidatorImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JSONObject fetch(String analysisId) {
        String msg = "unable to validate analysis, " + analysisId + ", for use in pipelines";
        try {
            MuleEventContext eventContext = RequestContext.getEventContext();
            MuleMessage message = new DefaultMuleMessage(new Object[]{daoFactory, analysisId});
            MuleMessage response = eventContext.sendEvent(message, "vm://validateAnalysisForPipelinesInternal");
            checkForExceptionPayload(msg, response);
            return (JSONObject) response.getPayload(JSONObject.class);
        }
        catch (MuleException e) {
            LOG.error(msg, e);
            throw new IPlantBusinessException(msg, e);
        }
    }
}
