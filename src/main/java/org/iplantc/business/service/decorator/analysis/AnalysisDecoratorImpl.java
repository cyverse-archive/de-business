package org.iplantc.business.service.decorator.analysis;

import net.sf.json.JSONObject;
import org.iplantc.workflow.dao.DaoFactory;

/**
 * An enumeration of analysis decorators.
 * 
 * @author Dennis Roberts
 */
public enum AnalysisDecoratorImpl implements AnalysisDecorator {

    PIPELINE {
        @Override
        public void decorate(DaoFactory daoFactory, JSONObject analysisListing) {
            PipelineAnalysisDecorator.defaultPipelineAnalysisDecorator(daoFactory).decorate(analysisListing);
        }
    },
    
    DEPLOYED_COMPONENT {
        @Override
        public void decorate(DaoFactory daoFactory, JSONObject analysisListing) {
            DeployedComponentAnalysisDecorator.defaultDecorator(daoFactory).decorate(analysisListing);
        }
    };

    /**
     * @return the string representation of the analysis decorator.
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    /**
     * @param str the string to look up.
     * @return the analysis decorator associated with the string.
     */
    public static AnalysisDecoratorImpl fromString(String str) {
        return valueOf(str.toUpperCase());
    }
}
