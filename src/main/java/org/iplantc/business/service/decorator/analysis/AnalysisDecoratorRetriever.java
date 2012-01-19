package org.iplantc.business.service.decorator.analysis;

/**
 * Used to retrieve analysis decorators.
 * 
 * @author Dennis Roberts
 */
public interface AnalysisDecoratorRetriever {

    /**
     * Retrieves an analysis decorator.
     * 
     * @param decoratorName the name of the analysis decorator.
     * @return the analysis decorator.
     */
    public AnalysisDecorator getDecorator(String decoratorName);
}
