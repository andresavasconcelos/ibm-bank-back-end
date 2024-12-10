package br.com.ibm.bank.controller;

import br.com.ibm.bank.domain.dto.TransferRequestDTO;
import br.com.ibm.bank.domain.entity.Transaction;
import br.com.ibm.bank.domain.exception.EmptyException;
import br.com.ibm.bank.domain.exception.ErrorResponse;
import br.com.ibm.bank.service.transaction.ITransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferController {

    @Autowired
    private final ITransactionService service;

    protected  static final Logger log = LogManager.getLogger();

    public TransferController(ITransactionService service) {
        this.service = service;
    }

    @PostMapping("/transfer/debit")
    public ResponseEntity<?> debitBalance(@RequestBody TransferRequestDTO dto){
        log.info("Starting method debit balance");
        try{

            validateParameters(dto.getAccountNumber(), dto.getAmount());

            service.debitBalance(dto.getAccountNumber(), dto.getAmount());

            log.info("Debit balance successfully");
            return ResponseEntity.status(HttpStatus.OK).build();

        }catch (Exception e) {

            log.error("Unexpected error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected Error", "An unexpected error occurred. Please contact support."));
        }
    }

    @PostMapping("/transfer/credit")
    public ResponseEntity<?> creditBalance(@RequestBody TransferRequestDTO dto){
        log.info("Starting method credit balance");
        try{

            validateParameters(dto.getAccountNumber(), dto.getAmount());

            service.creditBalance(dto.getAccountNumber(), dto.getAmount());

            log.info("Credit balance successfully");
            return ResponseEntity.status(HttpStatus.OK).build();

        }catch (Exception e) {

            log.error("Unexpected error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected Error", "An unexpected error occurred. Please contact support."));
        }
    }

    @GetMapping("/transfer/extract/{idAccount}")
    public ResponseEntity<?> extract(@RequestParam Integer idAccount){
        log.info("Starting method extract");
        try{

            List<Transaction> extractList =  service.extract(idAccount);

            log.info("extract successfully");
            return ResponseEntity.status(HttpStatus.OK).body(extractList);

        }catch (Exception e) {

            log.error("Unexpected error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected Error", "An unexpected error occurred. Please contact support."));
        }
    }

    private void validateParameters(Integer id, Double amount) throws EmptyException {
        if (id == null || amount == null)
                throw new EmptyException("Parameters is null", 99,
                        "id: " + id + " amount: " + amount);
            log.info("Parameters is not null");
    }
}
