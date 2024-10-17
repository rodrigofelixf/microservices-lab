package io.github.rodrigofelix.mscartoes.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CardOwner cardOwner;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public CreditCard(String name, CardOwner cardOwner, BigDecimal income, BigDecimal basicLimit) {
        this.name = name;
        this.cardOwner = cardOwner;
        this.income = income;
        this.basicLimit = basicLimit;
    }
}
