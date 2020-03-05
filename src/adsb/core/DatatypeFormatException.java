package adsb.core;

/**
 * DatatypeFormatException is a specific exception that will be used if the datatype portion the ADS-B message is in an unexpected value or length.
 * @author Evan
 */
public class DatatypeFormatException extends Exception {

    /**
     * Creates a new instance of <code>DatatypeFormatException</code> without
     * detail message.
     */
    public DatatypeFormatException() {
        System.exit(1);
    }

    /**
     * Constructs an instance of <code>DatatypeFormatException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DatatypeFormatException(String msg) {
        super(msg);
        System.exit(1);
    }
}
