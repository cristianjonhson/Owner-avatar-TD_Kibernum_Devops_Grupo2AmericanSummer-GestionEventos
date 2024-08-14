package cl.td.g2.eventos.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionHandler para capturar globalmente las excepciones y retornar una respuesta de error personalizada
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/**
     * Capturar las excepciones por una entidad no encontrada
     *
     * @param exception tipo de excepción a capturar
     * @return el response de la api
     */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	/**
     * Capturar las excepciones de las reglas de validación en los DTO
     *
     * @param exception tipo de excepción a capturar
     * @return el response de la api
     */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		Map<String, Object> errorMap = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), errorMap.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	/**
     * Capturar las excepciones por errores del request
     *
     * @param exception tipo de excepción a capturar
     * @return el response de la api
     */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException exception) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	/**
     * Capturar las excepciones por método inválido en el request
     *
     * @param exception tipo de excepción a capturar
     * @return el response de la api
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }
	
	/**
     * Capturar las excepciones generales o no personalizadas
     *
     * @param exception tipo de excepción a capturar
     * @return el response de la api
     */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(Exception exception) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
