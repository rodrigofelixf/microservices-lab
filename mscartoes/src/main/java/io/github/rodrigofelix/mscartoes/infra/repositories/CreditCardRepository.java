package io.github.rodrigofelix.mscartoes.infra.repositories;

import io.github.rodrigofelix.mscartoes.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    List<CreditCard> findByIncomeLessThanEqual(BigDecimal income);
}
