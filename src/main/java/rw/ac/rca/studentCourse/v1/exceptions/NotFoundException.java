package rw.ac.rca.studentCourse.v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rw.ac.rca.studentCourse.v1.dto.responses.ErrorResponse;
import rw.ac.rca.studentCourse.v1.dto.responses.Response;
import rw.ac.rca.studentCourse.v1.enums.ResponseType;

import java.util.ArrayList;
import java.util.List;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }

    public ResponseEntity<Response> getResponse(){
        List<String> details = new ArrayList<>();
        details.add(super.getMessage());
        ErrorResponse errorResponse = new ErrorResponse().setMessage("Failed to get a resource").setDetails(details);
        Response<ErrorResponse> response = new Response<>();
        response.setType(ResponseType.RESOURCE_NOT_FOUND);
        response.setPayload(errorResponse);
        return new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
    }
}