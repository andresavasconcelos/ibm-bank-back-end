package br.com.ibm.bank.controller;

import br.com.ibm.bank.domain.dto.AccountDTO;
import br.com.ibm.bank.domain.exception.ErrorResponse;
import br.com.ibm.bank.service.account.IAccountService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private final IAccountService service;

    protected  static final Logger log = LogManager.getLogger();

    public AccountController(IAccountService service){
        this.service = service;
    }

    @PostMapping("/account/find/{id}")
    public ResponseEntity<?> findAccount(@PathVariable Integer id){
        log.info("Starting method findAccount account");
        try{
            log.info("Starting method findAccount account");

            AccountDTO account = service.findAccount(id);

            log.info("Account found successfully");
            return ResponseEntity.status(HttpStatus.OK).body(account);

        }catch (Exception e) {

            log.error("Unexpected error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected Error", "An unexpected error occurred. Please contact support."));
        }
    }


}
