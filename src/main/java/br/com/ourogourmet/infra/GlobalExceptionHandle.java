package br.com.ourogourmet.infra;

import br.com.ourogourmet.usuario.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsuarioCamposInvalidosException.class)
    public ResponseEntity<ErrorMessage> handleCamposInvalidosException(UsuarioCamposInvalidosException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(UsuarioCriacaoFalhaException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioCriacaoFalhaException(UsuarioCriacaoFalhaException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(UsuarioDuplicadoException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioDuplicadoException(UsuarioDuplicadoException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(UsuarioValidacaoException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioValidacaoException(UsuarioValidacaoException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(UsuarioSenhaInvalidaException.class)
    public ResponseEntity<ErrorMessage> handleSenhaInvalidaException(UsuarioSenhaInvalidaException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatResponse);
    }

    @ExceptionHandler(UsuarioNaoDeletadoException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioNaoDeletadoException(UsuarioNaoDeletadoException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    // Fallback para qualquer erro inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatResponse);
    }
}
