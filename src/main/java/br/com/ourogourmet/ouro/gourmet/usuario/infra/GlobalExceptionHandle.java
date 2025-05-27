package br.com.ourogourmet.ouro.gourmet.usuario.infra;

import br.com.ourogourmet.ouro.gourmet.usuario.exceptions.UsuarioCamposInconsistentesException;
import br.com.ourogourmet.ouro.gourmet.usuario.exceptions.UsuarioJaExisteException;
import br.com.ourogourmet.ouro.gourmet.usuario.exceptions.UsuarioNaoExisteException;
import br.com.ourogourmet.ouro.gourmet.usuario.exceptions.UsuarioSenhaInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UsuarioNaoExisteException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioNotFoundException(UsuarioNaoExisteException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("erro", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UsuarioCamposInconsistentesException.class)
    public ResponseEntity<Map<String, String>> handleCamposInvalidos(UsuarioCamposInconsistentesException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("erro", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UsuarioSenhaInvalidaException.class)
    public ResponseEntity<Map<String, String>> handleSenhaInvalida(UsuarioSenhaInvalidaException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("erro", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UsuarioJaExisteException.class)
    public ResponseEntity<Map<String, String>> handleJaExiste(UsuarioJaExisteException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("erro", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // Fallback para qualquer erro inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("erro", "Erro interno: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
