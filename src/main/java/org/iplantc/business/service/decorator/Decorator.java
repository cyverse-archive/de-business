package org.iplantc.business.service.decorator;

/**
 * Decorates objects returned by a service.
 * 
 * @author Dennis Roberts
 */
public interface Decorator<T> {

    /**
     * Decorates an item.
     * 
     * @param item the item to decorate.
     */
    public void decorate(T item);
}
