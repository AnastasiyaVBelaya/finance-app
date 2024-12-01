package by.it_academy.jd2.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationDTO {

    @PositiveOrZero(message = "Номер страницы должен быть 0 или больше")
    private int page;

    @Positive(message = "Размер страницы должен быть больше 0")
    @Max(value = 100, message = "Размер страницы не должен превышать 100")
    private int size;
}









