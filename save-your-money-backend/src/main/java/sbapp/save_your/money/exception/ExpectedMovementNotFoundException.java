package sbapp.save_your.money.exception;

public class ExpectedMovementNotFoundException extends RuntimeException {
    public ExpectedMovementNotFoundException(String message){
        super(message);
    }
}
