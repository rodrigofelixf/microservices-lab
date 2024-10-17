package io.github.rodrigofelix.mscartoes.application;

import io.github.rodrigofelix.mscartoes.domain.CreditCard;
import io.github.rodrigofelix.mscartoes.infra.repositories.CreditCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Transactional
    public CreditCard save(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public List<CreditCard> getCardsIncomeLessThanEqual(Long income) {
        var bigDecimalIncome = BigDecimal.valueOf(income);
        return creditCardRepository.findByIncomeLessThanEqual(bigDecimalIncome);
    }
}
