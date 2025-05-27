package br.com.ourogourmet.ouro.gourmet.usuario.infra;

import br.com.ourogourmet.ouro.gourmet.usuario.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsuarioAlteracaoInvalidaException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioAlteracaoInvalidaException(UsuarioAlteracaoInvalidaException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(UsuarioNaoExisteException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioNotFoundException(UsuarioNaoExisteException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(UsuarioCamposInconsistentesException.class)
    public ResponseEntity<ErrorMessage> handleCamposInvalidos(UsuarioCamposInconsistentesException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(UsuarioSenhaInvalidaException.class)
    public ResponseEntity<ErrorMessage> handleSenhaInvalida(UsuarioSenhaInvalidaException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatResponse);
    }

    @ExceptionHandler(UsuarioLoginNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> handleLoginNaoEncontradoException(UsuarioLoginNaoEncontradoException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatResponse);
    }

    @ExceptionHandler(UsuarioJaExisteException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioJaExiste(UsuarioJaExisteException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    // Fallback para qualquer erro inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneric(Exception exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatResponse);
    }
}
