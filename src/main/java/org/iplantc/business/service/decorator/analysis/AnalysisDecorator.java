package org.iplantc.business.service.decorator.analysis;

import net.sf.json.JSONObject;
import org.iplantc.workflow.dao.DaoFactory;

/**
 * Used to decorate analysis listings.
 * 
 * @author Dennis Roberts
 */
public interface AnalysisDecorator {

    /**
     * Decorates a JSON object representing an analysis listing.
     * 
     * @param daoFactory used to obtain data access objects.
     * @param anlaysisListing the analysis listing.
     */
    public void decorate(DaoFactory daoFactory, JSONObject analysisListing);
}
