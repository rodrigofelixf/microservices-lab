package io.github.rodrigofelix.msclientes.application.representation;


import io.github.rodrigofelix.msclientes.domain.Client;
import lombok.Data;

@Data
public class ClientSaveRequest {
    private String document;
    private String name;
    private Integer age;



    public Client toModel() {
        return new Client(document, name, age);
    }
}
