package org.iplantc.business;

/**
 * Base exception class for all exceptions thrown by the IPlant business layer services.
 * 
 * @author Dennis Roberts
 */
public class IPlantBusinessException extends RuntimeException {

    /**
     * @param cause the cause of this exception.
     */
    public IPlantBusinessException(Throwable cause) {
        super(cause);
    }

    /**
     * @param msg the detail message.
     * @param cause the cause of this exception.
     */
    public IPlantBusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * @param msg the detail message.
     */
    public IPlantBusinessException(String msg) {
        super(msg);
    }

    /**
     * The default constructor.
     */
    public IPlantBusinessException() {
    }
}
