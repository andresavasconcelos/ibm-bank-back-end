package br.com.ibm.bank.controller;

import br.com.ibm.bank.domain.dto.CustomerDTO;
import br.com.ibm.bank.domain.entity.Customer;
import br.com.ibm.bank.domain.exception.EmptyException;
import br.com.ibm.bank.domain.exception.ErrorResponse;
import br.com.ibm.bank.service.customer.ICustomerService;
import jakarta.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final ICustomerService iCustomerService;
    protected  static final Logger log = LogManager.getLogger();

    public CustomerController(ICustomerService iCustomerService){
        this.iCustomerService = iCustomerService;
    }

    @PostMapping("/customer/create")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        log.info("Starting method create customer");
        try {

            validateCustomerParameters(customerDTO);

            Customer customer = iCustomerService.create(customerDTO);

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

    private void validateCustomerParameters(CustomerDTO customerDTO) throws EmptyException {
        if(customerDTO.getName().equals("") || customerDTO.getName() == null
                || customerDTO.getAge() == null || customerDTO.getEmail().isEmpty()
                || customerDTO.getEmail() == null)
            throw  new EmptyException("Parameters is null", 99,
                    "name: " + customerDTO.getName()
                    + " age:" + customerDTO.getAge()
                    + " email: " + customerDTO.getEmail() );

        log.info("Parameters is not null");
    }
}
