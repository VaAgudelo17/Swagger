package co.com.agudelo.microservice.resolveEnigmaApi.api;

import java.util.List;

import co.com.agudelo.microservice.resolveEnigmaApi.model.GetEnigmaRequest;
import co.com.agudelo.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.agudelo.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-03-03T16:34:14.965-05:00[America/Bogota]")


@Controller
public class GetStepApiController implements GetStepApi {

	 private static final Logger log = LoggerFactory.getLogger(GetStepApiController.class);

	    private final ObjectMapper objectMapper;

	    private final HttpServletRequest request;

	    @org.springframework.beans.factory.annotation.Autowired
	    public GetStepApiController(ObjectMapper objectMapper, HttpServletRequest request) {
	        this.objectMapper = objectMapper;
	        this.request = request;
	    } 
	    

	    public ResponseEntity<List<JsonApiBodyResponseSuccess>> getStep(@ApiParam(value = "request body get enigma step", required = true) @Valid @RequestBody JsonApiBodyRequest body) {
	    	 List<GetEnigmaRequest> enigmas = body.getData();
	         List<JsonApiBodyResponseSuccess> responseList = enigmas.stream()
	                 .map(this::createResponse)
	                 .collect(Collectors.toList());
	                 
	         return new ResponseEntity<>(responseList, HttpStatus.OK);      
	                
	        
	    }

	    private JsonApiBodyResponseSuccess createResponse(GetEnigmaRequest enigma) {
	        GetEnigmaStepResponse enigmaStepResponse = createEnigmaStepResponse(enigma);
	        JsonApiBodyResponseSuccess responseBody = new JsonApiBodyResponseSuccess();

	        responseBody.addDataItem(enigmaStepResponse);

	        return responseBody;
	    }

	    private GetEnigmaStepResponse createEnigmaStepResponse(GetEnigmaRequest enigma) {
	        String solution = solveEnigma(enigma.getEnigma());
	        GetEnigmaStepResponse enigmaStepResponse = new GetEnigmaStepResponse();

	        enigmaStepResponse.setHeader(enigma.getHeader());
	        enigmaStepResponse.setAnswer(solution);

	        return enigmaStepResponse;
	    }

	    private String solveEnigma(String enigmaQuestion) {
	        String enigmaExpected = "2";

	        if (!enigmaExpected.equals(enigmaQuestion)) {
	            throw new IllegalArgumentException("Enigma not supported: " + enigmaQuestion + " - Expected: " + enigmaExpected + " - Please, try again.");
	        }

	        return "Step 2: Put the giraffe inside";
	    }
 
    
    
    
    
    
    
    
    
}
