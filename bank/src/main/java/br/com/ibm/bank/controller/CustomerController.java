package br.com.ibm.bank.controller;

import br.com.ibm.bank.domain.dto.CustomerDTO;
import br.com.ibm.bank.domain.dto.ResponseCustomerDTO;
import br.com.ibm.bank.domain.entity.Customer;
import br.com.ibm.bank.domain.exception.EmptyException;
import br.com.ibm.bank.domain.exception.ErrorResponse;
import br.com.ibm.bank.service.customer.ICustomerService;
import jakarta.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    private final ICustomerService service;
    protected  static final Logger log = LogManager.getLogger();

    public CustomerController(ICustomerService service){
        this.service = service;
    }

    @PostMapping("/customer")
    public ResponseEntity<?> create(@RequestBody CustomerDTO customerDTO) {
        log.info("Starting method create customer");
        try {

            validateCustomerParameters(customerDTO);

            Customer customer = service.create(customerDTO);

            log.info("Customer created successfully with ID: {}", customer.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);

        } catch (ValidationException e) {

            log.warn("Validation failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Validation Error", e.getMessage()));

        } catch (EmptyException e) {

            log.error("Service error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new EmptyException("Service Error", 99, "Parameters is null"));

        } catch (Exception e) {

            log.error("Unexpected error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected Error", "An unexpected error occurred. Please contact support."));
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<?> find(@RequestParam String document) {
        log.info("Starting method create customer");
        try {

            ResponseCustomerDTO customer = service.findCustomerByDocument(document);

            log.info("Customer created successfully with ID: {}", customer.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(customer);

        } catch (ValidationException e) {

            log.warn("Validation failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Validation Error", e.getMessage()));

        } catch (Exception e) {

            log.error("Unexpected error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected Error", "An unexpected error occurred. Please contact support."));
        }
    }

    private void validateCustomerParameters(CustomerDTO customerDTO) throws EmptyException {
        if(customerDTO.getDocument().isEmpty() || customerDTO.getDocument() == null
                || customerDTO.getName().isEmpty() || customerDTO.getName() == null
                || customerDTO.getAge() == null || customerDTO.getEmail().isEmpty()
                || customerDTO.getEmail() == null)
            throw  new EmptyException("Parameters is null", 99,
                        "name: " + customerDTO.getName()
                        + " age: " + customerDTO.getAge()
                        + " email: " + customerDTO.getEmail() );
            log.info("Parameters is not null");
    }
}
