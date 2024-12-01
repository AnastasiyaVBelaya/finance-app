package by.it_academy.jd2.classifierService.controller;

import by.it_academy.jd2.classifierService.DTO.CurrencyDTO;
import by.it_academy.jd2.classifierService.service.api.ICurrencyService;
import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/classifier/currency")
public class CurrencyController {

    private final ICurrencyService currencyService;

    public CurrencyController(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public PageOf<CurrencyDTO> get(@Valid PaginationDTO paginationDTO) {
        return currencyService.findAll(paginationDTO);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void save(@Valid @RequestBody CurrencyDTO currencyDTO) {
        currencyService.save(currencyDTO);
    }
}
