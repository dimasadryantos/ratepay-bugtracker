package ratepay.bugtracker.service.exception;


public class BadRequestException extends Exception {

    private static final long serialVersionUID = 6137778791223481410L;

    public BadRequestException(String message) {
        super(message);
    }

}
