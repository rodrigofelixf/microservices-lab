package io.github.rodrigofelix.mscartoes.infra.repositories;

import io.github.rodrigofelix.mscartoes.domain.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {
    List<ClientCard> findByDocument(String document);
}
