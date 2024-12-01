package by.it_academy.jd2.exception;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException(String message) {
        super(message);
    }

    public EmailSendingException(String message, Throwable cause) {
        super(message, cause);
    }

    public static EmailSendingException withCause(Throwable cause) {
        return new EmailSendingException("Не удалось отправить email", cause);
    }
}
