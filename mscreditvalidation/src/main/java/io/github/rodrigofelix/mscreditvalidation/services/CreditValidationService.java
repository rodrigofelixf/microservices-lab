package io.github.rodrigofelix.mscreditvalidation.services;


import io.github.rodrigofelix.mscreditvalidation.domain.model.ClientCreditStatus;
import io.github.rodrigofelix.mscreditvalidation.domain.model.DataClient;
import io.github.rodrigofelix.mscreditvalidation.infra.clients.ResourceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreditValidationService {

    private final ResourceClient resourceClient;

    public CreditValidationService(ResourceClient resourceClient) {
        this.resourceClient = resourceClient;
    }


    public ClientCreditStatus getClientStatus(String document) {
        ResponseEntity<DataClient> clientData = resourceClient.clientData(document);
        return ClientCreditStatus
                .builder()
                .dataClient(clientData.getBody())
                .build();

    }
}
