package io.github.rodrigofelix.mscreditvalidation.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientCreditStatus {
    private DataClient dataClient;
    private List<ClientCreditCard> cards;
}
