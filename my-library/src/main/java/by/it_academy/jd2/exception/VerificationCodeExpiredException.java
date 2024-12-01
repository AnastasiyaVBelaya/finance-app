package by.it_academy.jd2.exception;

public class VerificationCodeExpiredException extends RuntimeException {

    public VerificationCodeExpiredException(String message) {
        super(message);
    }

    public VerificationCodeExpiredException() {
        super("Срок действия кода верификации истек");
    }
}
