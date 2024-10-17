package io.github.rodrigofelix.msclientes.infra.repository;

import io.github.rodrigofelix.msclientes.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long > {

    Optional<Client> getByDocument(String document);
}
