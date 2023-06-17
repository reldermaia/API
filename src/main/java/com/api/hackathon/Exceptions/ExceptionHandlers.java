package com.api.hackathon.Exceptions;

import com.api.hackathon.Models.ErrorResponse;
import com.azure.core.amqp.exception.AmqpException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.NoSuchElementException;
import static org.springframework.http.HttpStatus.*;


@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler { /* ESSA CLASSE TRATA DE QUASE TODAS EXCECOES,
ATRIBUINDO STATUS HTTP CORRETO PARA AS EXCECOES APRESENTADAS*/

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    private ErrorResponse handleNoSuchElementException(NoSuchElementException exception) {
        logger.error(exception.getMessage(), exception);
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return errorResponse;
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    private ErrorResponse handleIllegalArgumentException(IllegalArgumentException exception){
        logger.error(exception.getMessage(), exception);
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return errorResponse;
    }
    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    @ResponseStatus(UNAUTHORIZED)
    private ErrorResponse handleDataAccessException(DataAccessException exception){
        logger.error(exception.getMessage(), exception);
        ErrorResponse errorResponse = new ErrorResponse("FALHA NA CONEXAO COM O BANCO DE DADOS");
        return errorResponse;
    }
    @ExceptionHandler(AmqpException.class)
    @ResponseBody
    @ResponseStatus(UNAUTHORIZED)
    private ErrorResponse handleAmqpException(AmqpException exception){
        logger.error(exception.getMessage(), exception);
        ErrorResponse errorResponse = new ErrorResponse("FALHA NA CONEXAO COM O EVENTHUB");
        return errorResponse;

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable
            (HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        logger.error(exception.getMessage(), exception);
        return ResponseEntity.status(BAD_REQUEST)
                .body("VERIFIQUE OS DADOS DIGITADOS. CERTIFIQUE-SE DE DIGITAR APENAS NUMEROS!!!");
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request)     {
        logger.error(exception.getMessage(), exception);
        return ResponseEntity.status(BAD_REQUEST)
                .body(exception.getDetailMessageArguments());
    }

}
