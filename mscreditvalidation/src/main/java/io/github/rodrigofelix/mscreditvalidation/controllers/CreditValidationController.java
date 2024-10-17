package io.github.rodrigofelix.mscreditvalidation.controllers;


import io.github.rodrigofelix.mscreditvalidation.domain.model.ClientCreditStatus;
import io.github.rodrigofelix.mscreditvalidation.services.CreditValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-validation")
public class CreditValidationController {

    private final CreditValidationService creditValidationService;

    public CreditValidationController(CreditValidationService creditValidationService) {
        this.creditValidationService = creditValidationService;
    }


    @GetMapping
    public String status() {
        return "credit-validation - OK!";
    }


    @GetMapping(value = "/client-status", params = "document")
    public ResponseEntity<ClientCreditStatus> showClientStatus(@RequestParam("document") String document) {
        ClientCreditStatus clientStatus = creditValidationService.getClientStatus(document);
        return ResponseEntity.ok(clientStatus);

    }
}
