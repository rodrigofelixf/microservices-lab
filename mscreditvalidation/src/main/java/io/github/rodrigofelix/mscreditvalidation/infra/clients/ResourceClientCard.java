package io.github.rodrigofelix.mscreditvalidation.infra.clients;


import io.github.rodrigofelix.mscreditvalidation.domain.model.Card;
import io.github.rodrigofelix.mscreditvalidation.domain.model.ClientCreditCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cards")
public interface ResourceClientCard {
    @GetMapping(params = "document")
    ResponseEntity<List<ClientCreditCard>> getClientCardByDocument(@RequestParam("document") String document);
    @GetMapping("/income")
    ResponseEntity<List<Card>> getCreditIncomeAt(@RequestParam("income") Long income);
}
