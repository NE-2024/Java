package rw.ac.rca.studentCourse.v1.exceptions;

import rw.ac.rca.studentCourse.v1.dto.responses.ErrorResponse;
import rw.ac.rca.studentCourse.v1.dto.responses.Response;
import rw.ac.rca.studentCourse.v1.enums.ResponseType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class LoginFailedException extends Exception{
    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public LoginFailedException(String message){
        super(message);
    }

    ResponseEntity<Response> getResponseEntity(){
        List<String> details = new ArrayList<>();
        details.add(super.getMessage());
        ErrorResponse errorResponse = new ErrorResponse().setMessage("Login Failed").setDetails(details);
        Response<ErrorResponse> response = new Response<>();
        response.setType(ResponseType.LOGIN_FAILED);
        response.setPayload(errorResponse);
        return new ResponseEntity<Response>(response , HttpStatus.UNAUTHORIZED);
    }
}
