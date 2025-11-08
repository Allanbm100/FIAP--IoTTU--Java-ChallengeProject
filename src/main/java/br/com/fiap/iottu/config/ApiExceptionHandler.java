package br.com.fiap.iottu.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(
            ResponseStatusException ex,
            HttpServletRequest request) {

        if (request.getRequestURI().startsWith("/api/")) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", ex.getStatusCode().value());
            body.put("error", ex.getStatusCode().toString());
            body.put("message", ex.getReason());
            body.put("path", request.getRequestURI());

            return ResponseEntity
                    .status(ex.getStatusCode())
                    .body(body);
        }
        throw ex;
    }

    @ExceptionHandler({ IllegalStateException.class, IllegalArgumentException.class })
    public ResponseEntity<Map<String, Object>> handleIllegalStateException(
            RuntimeException ex,
            HttpServletRequest request) {

        if (request.getRequestURI().startsWith("/api/")) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("error", "BAD_REQUEST");

            String message = ex.getMessage();
            if (message != null && message.contains("}")) {
                if (message.contains("deleteHasMotorcycles")) {
                    message = "Não é possível excluir este registro porque ele está vinculado a outros registros. Remova os vínculos e tente novamente.";
                } else if (message.contains("deleteHasMotorcycle")) {
                    message = "Não é possível excluir esta tag porque ela está vinculada a uma moto. Desvincule a tag e tente novamente.";
                } else if (message.contains("deleteHasTags")) {
                    message = "Não é possível excluir porque existem tags vinculadas. Desvincule as tags e tente novamente.";
                } else if (message.contains("error.duplicate")) {
                    message = "Já existe um registro com estes dados. Por favor, verifique os dados informados.";
                } else if (message.contains("notFoundById") || message.contains("invalidId")) {
                    message = "Registro não encontrado.";
                } else if (message.contains("tagNotFound")) {
                    message = "Tag não encontrada.";
                } else if (message.contains("tagAlreadyAssigned")) {
                    message = "Esta tag já está atribuída a outra moto.";
                } else if (message.contains("statusNotFound")) {
                    message = "Status não encontrado.";
                } else if (message.contains("yardNotFound")) {
                    message = "Pátio não encontrado.";
                } else if (message.contains("notFoundByEmail")) {
                    message = "Usuário não encontrado.";
                } else if (message.contains("oauth2EmailInvalid")) {
                    message = "Email OAuth2 inválido.";
                } else if (message.contains("duplicateCode")) {
                    message = "Já existe uma antena com este código.";
                } else if (message.contains("duplicateCoordinates")) {
                    message = "Já existe uma antena com estas coordenadas.";
                } else {
                    message = "Operação inválida. Verifique os dados e tente novamente.";
                }
            }

            body.put("message", message);
            body.put("path", request.getRequestURI());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(body);
        }

        throw ex;
    }
}
