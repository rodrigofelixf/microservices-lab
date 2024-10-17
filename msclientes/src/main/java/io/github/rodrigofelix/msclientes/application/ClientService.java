package io.github.rodrigofelix.msclientes.application;

import io.github.rodrigofelix.msclientes.domain.Client;
import io.github.rodrigofelix.msclientes.infra.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Transactional
    public Client save(Client client){
        return clientRepository.save(
                client
        );
    }

    public Optional<Client> getByDocument(String document) {
        return clientRepository.getByDocument(document);
    }


}
