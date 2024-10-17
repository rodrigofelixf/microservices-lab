package io.github.rodrigofelix.mscartoes.application;


import io.github.rodrigofelix.mscartoes.domain.ClientCard;
import io.github.rodrigofelix.mscartoes.domain.CreditCard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
public class CreditCardController {

    private final CreditCardService creditCardService;
    private final ClientCardService clientCardService;

    public CreditCardController(CreditCardService creditCardService, ClientCardService clientCardService) {
        this.creditCardService = creditCardService;
        this.clientCardService = clientCardService;
    }

    @GetMapping
    public String status() {

        return "Ok!";
    }

    @PostMapping
    public ResponseEntity<Void> registry(@RequestBody CardSaveRequest cardSaveRequest) {
        CreditCard card = cardSaveRequest.toModel();
        creditCardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping(params = "income")
    public ResponseEntity<List<CreditCard>> getCreditIncomeAt(@RequestParam("income") Long income) {
        List<CreditCard> list = creditCardService.getCardsIncomeLessThanEqual(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "document")
    public ResponseEntity<List<CardPerClientResponse>> getClientCardByDocument(@RequestParam("document") String document) {
        List<ClientCard> list = clientCardService.listCardByDocument(document);
        var resultList = list.stream()
                .map(CardPerClientResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }

}
