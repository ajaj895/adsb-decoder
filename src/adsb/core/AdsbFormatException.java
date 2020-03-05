
package adsb.core;

/**
 * AdsbFormatException is an exception meant to be thrown when the ADS-B message does not meet expected values or length.
 * @author Evan
 */
public class AdsbFormatException extends Exception {

    /**
     * Creates a new instance of <code>AdsbFormatException</code> without detail
     * message.
     */
    public AdsbFormatException() {
    }

    /**
     * Constructs an instance of <code>AdsbFormatException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AdsbFormatException(String msg) {
        super(msg);
    }
}
