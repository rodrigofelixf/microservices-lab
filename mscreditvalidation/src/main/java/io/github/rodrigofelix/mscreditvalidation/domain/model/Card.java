package io.github.rodrigofelix.mscreditvalidation.domain.model;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Card {
    private Long id;
    private String name;
    private String cardOwner;
    private BigDecimal basicLimit;
}
