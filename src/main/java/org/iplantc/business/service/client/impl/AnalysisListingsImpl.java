package org.iplantc.business.service.client.impl;

import net.sf.json.JSONObject;
import org.iplantc.business.IPlantBusinessException;
import org.iplantc.business.service.client.AnalysisListings;
import org.iplantc.workflow.dao.DaoFactory;
import org.mule.DefaultMuleMessage;
import org.mule.RequestContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;

/**
 * Used to obtain analysis listings from lower-level services.
 * 
 * @author Dennis Roberts
 */
public class AnalysisListingsImpl extends AbstractMuleClient implements AnalysisListings {

    /**
     * Used to obtain data access objects.
     */
    private DaoFactory daoFactory;

    /**
     * @param daoFactory used to obtain data access objects.
     */
    public AnalysisListingsImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JSONObject getAnalysesInGroup(String analysisGroupId) {
        String msg = "unable to retrieve analyses in group " + analysisGroupId;
        try {
            MuleEventContext eventContext = RequestContext.getEventContext();
            MuleMessage message = new DefaultMuleMessage(new Object[]{daoFactory, analysisGroupId});
            MuleMessage response = eventContext.sendEvent(message, "vm://getAnalysesInGroupInternal");
            checkForExceptionPayload(msg, response);
            return (JSONObject) response.getPayload(JSONObject.class);
        }
        catch (MuleException e) {
            LOG.error(msg, e);
            throw new IPlantBusinessException(msg, e);
        }
    }
}
