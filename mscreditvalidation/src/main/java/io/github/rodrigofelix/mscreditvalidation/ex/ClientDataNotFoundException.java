package io.github.rodrigofelix.mscreditvalidation.ex;

public class ClientDataNotFoundException extends Exception {
    public ClientDataNotFoundException() {
        super("Client data not found for the provided document.");
    }
}
