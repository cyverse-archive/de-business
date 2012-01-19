package org.iplantc.business.service.decorator;

/**
 * Used to retrieve decorations.
 * 
 * @author Dennis Roberts
 */
public interface DecorationRetriever<I, O> {

    /**
     * Fetches decorations.
     * 
     * @param arg the argument required to get the decoration.
     * @return the decoration for the argument.
     */
    public O fetch(I arg);
}
