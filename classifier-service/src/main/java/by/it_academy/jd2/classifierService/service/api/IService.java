package by.it_academy.jd2.classifierService.service.api;

import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;

public interface IService<T>{
    void save(T t);
    PageOf<T> findAll(PaginationDTO paginationDTO);
}
