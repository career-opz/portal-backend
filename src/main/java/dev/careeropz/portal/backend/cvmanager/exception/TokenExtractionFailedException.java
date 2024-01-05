package dev.careeropz.portal.backend.cvmanager.exception;

public class TokenExtractionFailedException extends RuntimeException{
    public TokenExtractionFailedException(String ex) {
        super(String.format("TokenExtractionFailedException: %s", ex));
    }
}
