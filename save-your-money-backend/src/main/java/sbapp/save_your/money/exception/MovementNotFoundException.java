package sbapp.save_your.money.exception;

public class MovementNotFoundException extends RuntimeException {
    public MovementNotFoundException(String message){
        super(message);
    }
}
