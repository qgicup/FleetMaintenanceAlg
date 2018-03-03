package exception;

/**
 * Created with ♥ by georgeplaton on 03.03.18.
 */
public class FleetException extends Exception {

    private FleetExceptionType exceptionType = FleetExceptionType.UNKNOWN;


    public FleetException(String message) {
        super(message);
    }

    public FleetException(FleetExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }

    // Getters/Setters

    public FleetExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(FleetExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
}
