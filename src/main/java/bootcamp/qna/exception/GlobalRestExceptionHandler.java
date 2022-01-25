package bootcamp.qna.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("bootcamp.qna.api")
public class GlobalRestExceptionHandler {
	
	@ExceptionHandler(GeneralHttpException.class)
	public ResponseEntity<RestErrorResponse> handleHttpException(GeneralHttpException ghe){
		RestErrorResponse rer = new RestErrorResponse(ghe.getMessage());
		return new ResponseEntity<>(rer, ghe.getStatus());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<RestErrorResponse> handleRuntime(RuntimeException ghe){
		RestErrorResponse rer = new RestErrorResponse(ghe.getMessage());
		return new ResponseEntity<>(rer, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
