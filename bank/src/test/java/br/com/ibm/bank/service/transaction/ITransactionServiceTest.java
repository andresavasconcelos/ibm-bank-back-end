package br.com.ibm.bank.service.transaction;

import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.domain.entity.Transaction;
import br.com.ibm.bank.domain.enums.AccountStatus;
import br.com.ibm.bank.repository.TransactionRepository;
import br.com.ibm.bank.service.account.IAccountService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ITransactionServiceTest {

    @Mock
    private IAccountService accountService;

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreditBalance_Success() throws ValidationException, jakarta.xml.bind.ValidationException {

        Account mockAccount = new Account();
        mockAccount.setId(1);
        mockAccount.setBalance(500.00);
        mockAccount.setStatus(AccountStatus.ACTIVE);

        when(accountService.findByNumberAccount(1)).thenReturn(mockAccount);
        when(repository.save(any(Transaction.class))).thenReturn(new Transaction());

        transactionService.creditBalance(1, 100.00);

        verify(accountService, times(1)).findByNumberAccount(1);
        verify(repository, times(1)).save(any(Transaction.class));
        assertEquals(400.00, mockAccount.getBalance());
    }

    @Test
    void testCreditBalance_InsufficientBalance() {

        Account mockAccount = new Account();
        mockAccount.setId(1);
        mockAccount.setBalance(50.00);
        mockAccount.setStatus(AccountStatus.ACTIVE);

        when(accountService.findByNumberAccount(1)).thenReturn(mockAccount);

        ValidationException exception = assertThrows(ValidationException.class, () -> transactionService.creditBalance(1, 100.00));
        assertEquals("Não há salvo para efetuar a transação", exception.getMessage());
        verify(accountService, times(1)).findByNumberAccount(1);
        verify(repository, never()).save(any(Transaction.class));
    }

    @Test
    void testCreditBalance_AccountNotActive() {

        Account mockAccount = new Account();
        mockAccount.setId(1);
        mockAccount.setBalance(500.00);
        mockAccount.setStatus(AccountStatus.CLOSED);

        when(accountService.findByNumberAccount(1)).thenReturn(mockAccount);

        ValidationException exception = assertThrows(ValidationException.class, () -> transactionService.creditBalance(1, 100.00));
        assertEquals("Conta nao existe ou não está ativa", exception.getMessage());
        verify(accountService, times(1)).findByNumberAccount(1);
        verify(repository, never()).save(any(Transaction.class));
    }

    @Test
    void testDebitBalance_Success() {

        Account mockAccount = new Account();
        mockAccount.setId(1);
        mockAccount.setBalance(500.00);
        mockAccount.setStatus(AccountStatus.ACTIVE);

        when(accountService.findByNumberAccount(1)).thenReturn(mockAccount);
        when(repository.save(any(Transaction.class))).thenReturn(new Transaction());

        transactionService.debitBalance(1, 100.00);

        verify(accountService, times(1)).findByNumberAccount(1);
        verify(repository, times(1)).save(any(Transaction.class));
        assertEquals(600.00, mockAccount.getBalance());
    }

    @Test
    void testDebitBalance_AccountNotFound() {

        when(accountService.findByNumberAccount(1)).thenReturn(null);

        ValidationException exception = assertThrows(ValidationException.class, () -> transactionService.debitBalance(1, 100.00));
        assertEquals("Conta nao existe ou não está ativa", exception.getMessage());
        verify(accountService, times(1)).findByNumberAccount(1);
        verify(repository, never()).save(any(Transaction.class));
    }
}