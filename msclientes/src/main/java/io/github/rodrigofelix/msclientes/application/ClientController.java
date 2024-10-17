package io.github.rodrigofelix.msclientes.application;

import io.github.rodrigofelix.msclientes.application.representation.ClientSaveRequest;
import io.github.rodrigofelix.msclientes.domain.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clients")
@Slf4j
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String status() {
        log.info("Obtain Status of clients microservice ");
        return "Ok";
    }


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ClientSaveRequest clientSaveRequest) {
        var client = clientSaveRequest.toModel();
        clientService.save(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("/client/document={document}")
                .buildAndExpand(client.getDocument())
                .toUri();
        return ResponseEntity.created(headerLocation).build();

    }


    @GetMapping("/client/{document}")
    public ResponseEntity<Client> clientData(@PathVariable("document") String document) {
        Client client = clientService.getByDocument(document)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para o documento: " + document));
        return ResponseEntity.ok(client);
    }




}
