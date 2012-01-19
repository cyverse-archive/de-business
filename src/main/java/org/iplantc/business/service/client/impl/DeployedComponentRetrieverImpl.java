package org.iplantc.business.service.client.impl;

import net.sf.json.JSONObject;
import org.iplantc.business.IPlantBusinessException;
import org.iplantc.business.service.client.DeployedComponentRetriever;
import org.iplantc.workflow.dao.DaoFactory;
import org.mule.DefaultMuleMessage;
import org.mule.RequestContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;

/**
 * Retrieves deployed components in an analysis from lower-level services.
 * 
 * @author Dennis Roberts
 */
public class DeployedComponentRetrieverImpl extends AbstractMuleClient implements DeployedComponentRetriever {

    /**
     * Used to obtain data access objects.
     */
    private DaoFactory daoFactory;

    /**
     * @param daoFactory used to obtain data access objects.
     */
    public DeployedComponentRetrieverImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Retrieves the deployed components for the analysis with the given identifier.
     * 
     * @param analysisId the analysis identifier.
     * @return a JSON object representing the list of deployed components.
     */
    @Override
    public JSONObject fetch(String analysisId) {
        String msg = "unable to get the deployed components for anlaysis, " + analysisId;
        try {
            MuleEventContext eventContext = RequestContext.getEventContext();
            MuleMessage message = new DefaultMuleMessage(new Object[]{daoFactory, analysisId});
            MuleMessage response = eventContext.sendEvent(message, "vm://listDeployedComponentsInAnalysis");
            checkForExceptionPayload(msg, response);
            return (JSONObject) response.getPayload(JSONObject.class);
        }
        catch (MuleException e) {
            LOG.error(msg, e);
            throw new IPlantBusinessException(msg, e);
        }
    }
}
