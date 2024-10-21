package io.github.rodrigofelix.mscreditvalidation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientCreditValidationResponse {
    private List<ApprovedCard> approvedCards;

}
