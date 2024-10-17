package io.github.rodrigofelix.mscartoes.application;


import io.github.rodrigofelix.mscartoes.domain.CardOwner;
import io.github.rodrigofelix.mscartoes.domain.CreditCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequest {
    private String name;
    private CardOwner cardOwner;
    private BigDecimal income;
    private BigDecimal basicLimit;



    public CreditCard toModel() {
        return new CreditCard(
                name, cardOwner, income, basicLimit
        );
    }
}
