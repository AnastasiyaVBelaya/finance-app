package by.it_academy.jd2.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException() {
        super("Пользователь с таким email уже существует");
    }

    public static EmailAlreadyExistsException withEmail(String email) {
        String message = String.format("Пользователь с таким email уже существует: %s", email);
        return new EmailAlreadyExistsException(message);
    }
}
