package io.github.rodrigofelix.mscreditvalidation.controllers;


import io.github.rodrigofelix.mscreditvalidation.domain.model.ClientCreditStatus;
import io.github.rodrigofelix.mscreditvalidation.domain.model.ClientCreditValidationResponse;
import io.github.rodrigofelix.mscreditvalidation.domain.model.DataClientValidate;
import io.github.rodrigofelix.mscreditvalidation.ex.ClientDataNotFoundException;
import io.github.rodrigofelix.mscreditvalidation.ex.ErrorCommunicationMicroservicesException;
import io.github.rodrigofelix.mscreditvalidation.services.CreditValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

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
    public ResponseEntity showClientStatus(@RequestParam("document") String document) {

        try {
            ClientCreditStatus clientStatus = creditValidationService.getClientStatus(document);
            return ResponseEntity.ok(clientStatus);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorCommunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity validateClientCredit(@RequestBody DataClientValidate dataClientValidate) {

        try {
            ClientCreditValidationResponse clientCreditResponse = creditValidationService.validateClientCredit(
                    dataClientValidate.getDocument(), dataClientValidate.getIncome());
            return ResponseEntity.ok(clientCreditResponse);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorCommunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
