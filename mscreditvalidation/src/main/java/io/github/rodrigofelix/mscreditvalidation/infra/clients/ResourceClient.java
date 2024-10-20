package io.github.rodrigofelix.mscreditvalidation.infra.clients;


import io.github.rodrigofelix.mscreditvalidation.domain.model.Card;
import io.github.rodrigofelix.mscreditvalidation.domain.model.DataClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "msclientes", path = "/clients")
public interface ResourceClient {

    @GetMapping(params = "document")
    ResponseEntity<DataClient> clientData(@RequestParam("document") String document);

}
