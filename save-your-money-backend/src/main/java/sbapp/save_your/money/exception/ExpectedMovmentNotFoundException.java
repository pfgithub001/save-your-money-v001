package sbapp.save_your.money.exception;

public class ExpectedMovmentNotFoundException extends RuntimeException {
    public ExpectedMovmentNotFoundException(String message){
        super(message);
    }
}
