package by.it_academy.jd2.accountService.service.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "classifier-service", url = "${classifier-service.base-url}")
public interface ClassifierFeignClient {
    @GetMapping("${classifier-service.urls.currency}/{uuid}")
    UUID getCurrencyByUuid(@PathVariable("uuid") UUID currencyUuid);

    @GetMapping("${classifier-service.urls.category}/{uuid}")
    UUID getCategoryByUuid(@PathVariable("uuid") UUID categoryUuid);
}
