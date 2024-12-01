package by.it_academy.jd2.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {
    ERROR("error"),
    STRUCTURED_ERROR("structured_error");

    private final String value;
}
