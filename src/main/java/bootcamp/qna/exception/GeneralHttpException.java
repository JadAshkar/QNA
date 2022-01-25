package bootcamp.qna.exception;

import org.springframework.http.HttpStatus;

public class GeneralHttpException extends RuntimeException {

	private static final long serialVersionUID = 1446743587245933895L;
	private HttpStatus status;
	
	public GeneralHttpException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
