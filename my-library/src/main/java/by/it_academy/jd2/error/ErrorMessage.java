package by.it_academy.jd2.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    GENERAL_ERROR("Сервер не смог корректно обработать запрос. Пожалуйста, обратитесь к администратору"),
    AUTH_ERROR("Пользователь не авторизован. Пожалуйста, выполните вход"),
    ACCESS_DENIED("У вас нет доступа к этому ресурсу"),
    INVALID_REQUEST("Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз");

    private final String message;
}
