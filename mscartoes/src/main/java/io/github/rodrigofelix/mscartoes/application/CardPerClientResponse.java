package io.github.rodrigofelix.mscartoes.application;


import io.github.rodrigofelix.mscartoes.domain.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardPerClientResponse {
    private String name;
    private String cardOwner;
    private BigDecimal displayLimit;

    public static CardPerClientResponse fromModel(ClientCard clientCard) {
        return new CardPerClientResponse(
                clientCard.getCreditCard().getName(),
                clientCard.getCreditCard().getCardOwner().toString(),
                clientCard.getBasicLimit()
        );
    }
}
