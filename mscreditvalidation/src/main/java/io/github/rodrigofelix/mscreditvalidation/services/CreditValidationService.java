package io.github.rodrigofelix.mscreditvalidation.services;


import feign.FeignException;
import io.github.rodrigofelix.mscreditvalidation.domain.model.*;
import io.github.rodrigofelix.mscreditvalidation.ex.ClientDataNotFoundException;
import io.github.rodrigofelix.mscreditvalidation.ex.ErrorCommunicationMicroservicesException;
import io.github.rodrigofelix.mscreditvalidation.infra.clients.ResourceClient;
import io.github.rodrigofelix.mscreditvalidation.infra.clients.ResourceClientCard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditValidationService {

    private final ResourceClient resourceClient;
    private final ResourceClientCard resourceClientCard;

    public CreditValidationService(ResourceClient resourceClient, ResourceClientCard resourceClientCard) {
        this.resourceClient = resourceClient;
        this.resourceClientCard = resourceClientCard;
    }


    public ClientCreditStatus getClientStatus(String document) throws ClientDataNotFoundException, ErrorCommunicationMicroservicesException {
        try {
            ResponseEntity<DataClient> clientData = resourceClient.clientData(document);
            ResponseEntity<List<ClientCreditCard>> clientCreditCard = resourceClientCard.getClientCardByDocument(document);
            return ClientCreditStatus
                    .builder()
                    .dataClient(clientData.getBody())
                    .cards(clientCreditCard.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException();
            }
            throw new ErrorCommunicationMicroservicesException(e.getMessage(), status);
        }




    }

    public ClientCreditValidationResponse validateClientCredit(String document, Long income) throws ClientDataNotFoundException, ErrorCommunicationMicroservicesException {

        try {
            ResponseEntity<DataClient> dataClientResponseEntity = resourceClient.clientData(document);
            ResponseEntity<List<Card>> cardResponse = resourceClientCard.getCreditIncomeAt(income);

            List<Card> cards = cardResponse.getBody();

            assert cards != null;
            List<ApprovedCard> listApprovedCards = cards.stream().map(card -> {
                var clientData = dataClientResponseEntity.getBody();
                var basicLimit = card.getBasicLimit();
                assert clientData != null;
                var ageBD = BigDecimal.valueOf(clientData.getAge());

                BigDecimal factor = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = factor.multiply(basicLimit);


                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setCard(card.getName());
                approvedCard.setCardOwner(card.getCardOwner());
                approvedCard.setApprovedLimit(approvedLimit);
                return approvedCard;
            }).collect(Collectors.toList());

            return new ClientCreditValidationResponse(listApprovedCards);
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException();
            }
            throw new ErrorCommunicationMicroservicesException(e.getMessage(), status);
        }
    }

}
