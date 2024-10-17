package io.github.rodrigofelix.mscreditvalidation.infra.clients;


import io.github.rodrigofelix.mscreditvalidation.domain.model.DataClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "msclientes", path = "/clients")
public interface ResourceClient {

    @GetMapping("/client/{document}")
    ResponseEntity<DataClient> clientData(@PathVariable("document") String document);
}
