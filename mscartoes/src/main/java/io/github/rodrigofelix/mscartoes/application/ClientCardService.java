package io.github.rodrigofelix.mscartoes.application;

import io.github.rodrigofelix.mscartoes.domain.ClientCard;
import io.github.rodrigofelix.mscartoes.infra.repositories.ClientCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientCardService {

    private final ClientCardRepository clientCardRepository;

    public ClientCardService(ClientCardRepository clientCardRepository) {
        this.clientCardRepository = clientCardRepository;
    }

    public List<ClientCard> listCardByDocument(String document) {
        return clientCardRepository.findByDocument(document);
    }
}
