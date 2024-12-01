package by.it_academy.jd2.classifierService.controller;

import by.it_academy.jd2.classifierService.service.ClassifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ClassifierController {
    private ClassifierService classifierService;

    public ClassifierController(ClassifierService classifierService) {
        this.classifierService = classifierService;
    }

    @GetMapping("/classifier/currency/{uuid}")
    public UUID getCurrencyByUuid(@PathVariable("uuid") UUID uuid) {
        return classifierService.getCurrencyByUuid(uuid);
    }

    @GetMapping("/classifier/operation/category/{uuid}")
    public UUID getCategoryByUuid(@PathVariable("uuid") UUID uuid) {
        return classifierService.getCategoryByUuid(uuid);
    }
}
