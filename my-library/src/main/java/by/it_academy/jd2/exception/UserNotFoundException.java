package by.it_academy.jd2.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("Пользователь не найден");
    }

    public static UserNotFoundException withUuid(String uuid) {
        String message = String.format("Пользователь с таким UUID не найден: %s", uuid);
        return new UserNotFoundException(message);
    }
}