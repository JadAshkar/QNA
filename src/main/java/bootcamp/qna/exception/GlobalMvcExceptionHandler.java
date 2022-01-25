package bootcamp.qna.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("bootcamp.qna.controller")
public class GlobalMvcExceptionHandler {
	
	@ExceptionHandler(GeneralHttpException.class)
	public String handleHttpException(GeneralHttpException ghe, Model model) {
		model.addAttribute("message", ghe.getMessage());
		model.addAttribute("status", ghe.getStatus());
		return "exceptions/custom_error";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handleHttpException(RuntimeException ghe, Model model) {
		model.addAttribute("message", ghe.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return "exceptions/custom_error";
	}
}
