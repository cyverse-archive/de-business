package org.iplantc.business.service.decorator.analysis;

/**
 * Retrieves analysis decorators.
 * 
 * @author Dennis Roberts
 */
public class AnalysisDecoratorRetrieverImpl implements AnalysisDecoratorRetriever {

    /**
     * {@inheritDoc}
     */
    @Override
    public AnalysisDecorator getDecorator(String decoratorName) {
        return AnalysisDecoratorImpl.fromString(decoratorName);
    }
}
