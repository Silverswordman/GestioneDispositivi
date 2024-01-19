package giuliasilvestrini.GestioneDispositivi.exceptions;


import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import giuliasilvestrini.GestioneDispositivi.payloads.ErrorsPayload;


import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    //gestione bad request
    @ExceptionHandler(BadRequestException.class)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleBadRequest(BadRequestException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    //gestione not found
    @ExceptionHandler(NotFoundException.class)

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotFoundException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    // Tutti gli altri tipi di eccezioni (come errori vari nel codice) verranno gestiti da questo metodo
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Dovrà rispondere con un 500
    public ErrorsPayload handleGenericError(Exception ex) {
        ex.printStackTrace();
        return new ErrorsPayload("Problema lato server! Giuro che risolveremo presto!", LocalDateTime.now());
    }
}