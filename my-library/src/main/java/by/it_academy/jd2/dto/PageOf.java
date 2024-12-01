package by.it_academy.jd2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageOf<T> {

    private int number;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private int numberOfElements;
    private boolean last;
    private List<T> content;

}
