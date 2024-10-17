package io.github.rodrigofelix.mscartoes.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class ClientCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String document;
    @ManyToOne
    @JoinColumn(name = "id_creditCard")
    private CreditCard creditCard;
    private BigDecimal basicLimit;
}
