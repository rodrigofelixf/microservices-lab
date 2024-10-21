package io.github.rodrigofelix.mscreditvalidation.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCard {
    private String card;
    private String cardOwner;
    private BigDecimal approvedLimit;
}
