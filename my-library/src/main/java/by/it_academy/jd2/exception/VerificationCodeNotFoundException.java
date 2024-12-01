package by.it_academy.jd2.exception;

public class VerificationCodeNotFoundException extends RuntimeException {

    public VerificationCodeNotFoundException(String message) {
        super(message);
    }

    public VerificationCodeNotFoundException() {
        super("Неверный код верификации");
    }
}
