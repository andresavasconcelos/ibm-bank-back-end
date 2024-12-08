package br.com.ibm.bank.service.account;

import br.com.ibm.bank.domain.dto.AccountDTO;
import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.domain.enums.AccountStatus;
import br.com.ibm.bank.domain.enums.AccountType;
import br.com.ibm.bank.domain.enums.Branch;
import br.com.ibm.bank.repository.AccountRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IAccountServiceTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountServiceImpl service;

    private final String END_DOCUMENT = "123456789000";
    private final Integer ACCOUNT_ID = 123;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        
        Account accountMock = new Account();
        accountMock.setNumberAccount(12341234);
        accountMock.setBranch(Branch.BRANCH_VILMARIANA_SAOPAULO.getValue());
        accountMock.setAccountType(AccountType.SAVINGS);
        accountMock.setBalance(0.0);
        accountMock.setStatus(AccountStatus.ACTIVE);
        accountMock.setCreateDate(LocalDate.now());

        when(repository.save(any(Account.class))).thenReturn(accountMock);

        Account result = service.create(END_DOCUMENT);

        assertNotNull(result);
        assertEquals(12341234, result.getNumberAccount());
        assertEquals(Branch.BRANCH_VILMARIANA_SAOPAULO, result.getBranch());
        verify(repository, times(1)).save(any(Account.class));
    }

    @Test
    void testFindAccount_Success() {

        Account accountMock = new Account();
        accountMock.setNumberAccount(ACCOUNT_ID);
        accountMock.setBranch(Branch.BRANCH_VILMARIANA_SAOPAULO.getValue());
        accountMock.setAccountType(AccountType.SAVINGS);
        accountMock.setBalance(100.0);

        when(repository.findByNumberAccount(ACCOUNT_ID)).thenReturn(accountMock);

        AccountDTO result = service.findAccount(ACCOUNT_ID);

        assertNotNull(result);
        assertEquals(ACCOUNT_ID, result.getNumberAccount());
        assertEquals(Branch.BRANCH_VILMARIANA_SAOPAULO, result.getBranch());
        verify(repository, times(1)).findByNumberAccount(ACCOUNT_ID);
    }

    @Test
    void testFindAccount_NotFound() {

        when(repository.findByNumberAccount(ACCOUNT_ID)).thenReturn(null);

        Exception exception = assertThrows(ValidationException.class, () -> service.findAccount(ACCOUNT_ID));
        assertEquals("Account not found", exception.getMessage());
        verify(repository, times(1)).findByNumberAccount(ACCOUNT_ID);
    }

    @Test
    void testGenerateAccountNumber() {

        Integer result = AccountServiceImpl.generateAccountNumber(END_DOCUMENT);

        assertNotNull(result);
        assertEquals(Integer.valueOf("1234" + END_DOCUMENT), result);
    }
}