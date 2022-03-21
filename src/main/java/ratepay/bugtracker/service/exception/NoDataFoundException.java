package ratepay.bugtracker.service.exception;



public class NoDataFoundException extends Exception {

    private static final long serialVersionUID = 5242327879915199662L;

    public NoDataFoundException(String message) {
        super(message);
    }
}
