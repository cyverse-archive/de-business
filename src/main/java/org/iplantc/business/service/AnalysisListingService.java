package org.iplantc.business.service;

import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.iplantc.business.service.client.AnalysisListings;
import org.iplantc.business.service.client.impl.AnalysisListingsImpl;
import org.iplantc.business.service.decorator.analysis.AnalysisDecoratorImpl;
import org.iplantc.business.service.decorator.analysis.AnalysisDecoratorRetriever;
import org.iplantc.business.service.decorator.analysis.AnalysisDecoratorRetrieverImpl;
import org.iplantc.business.service.helper.AnalysisLister;
import org.iplantc.hibernate.util.SessionTask;
import org.iplantc.hibernate.util.SessionTaskWrapper;
import org.iplantc.workflow.dao.DaoFactory;
import org.iplantc.workflow.dao.hibernate.HibernateDaoFactory;

/**
 * Services for listing analyses in the DE.
 * 
 * @author Dennis Roberts
 */
public class AnalysisListingService {

    /**
     * The Hibernate session factory.
     */
    private SessionFactory sessionFactory;

    /**
     * @param sessionFactory the Hibernate session factory.
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Lists analyses in an analysis group, including information to determine whether or not each analysis
     * can be included in a pipeline.
     * 
     * @param analysisGroupId the identifier of the analysis group.
     * @return A JSON string representing the list of analyses.
     */
    public String listAnalysesForPipeline(String analysisGroupId) {
        List<String> decoratorNames = Arrays.asList(AnalysisDecoratorImpl.PIPELINE.toString(),
                AnalysisDecoratorImpl.DEPLOYED_COMPONENT.toString());
        return listDecoratedAnalyses(analysisGroupId, decoratorNames);
    }

    /**
     * Lists analyses in an analysis group.
     * 
     * @param analysisGroupId the identifier of the analysis group.
     * @return a JSON string representing the list of analyses.
     */
    public String listAnalyses(String analysisGroupId) {
        List<String> decoratorNames = Arrays.asList(AnalysisDecoratorImpl.DEPLOYED_COMPONENT.toString());
        return listDecoratedAnalyses(analysisGroupId, decoratorNames);
    }

    /**
     * Lists the analyses in an analysis group, including the specified decorations.
     * 
     * @param analysisGroupId the analysis group identifier.
     * @param decoratorNames the list of decorator names.
     * @return the decorated analysis listing.
     */
    private String listDecoratedAnalyses(final String analysisGroupId, final List<String> decoratorNames) {
        return new SessionTaskWrapper(sessionFactory).performTask(new SessionTask<String>() {
            @Override
            public String perform(Session session) {
                DaoFactory daoFactory = new HibernateDaoFactory(session);
                AnalysisListings listingRetriever = new AnalysisListingsImpl(daoFactory);
                AnalysisDecoratorRetriever decoratorRetriever = new AnalysisDecoratorRetrieverImpl();
                AnalysisLister analysisLister = new AnalysisLister(listingRetriever, decoratorRetriever);
                return analysisLister.listAnalysesInGroup(analysisGroupId, decoratorNames, daoFactory).toString();
            }
        });
    }
}
