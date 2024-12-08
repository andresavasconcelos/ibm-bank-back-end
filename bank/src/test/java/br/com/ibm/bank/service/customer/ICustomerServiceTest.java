package br.com.ibm.bank.service.customer;

import br.com.ibm.bank.domain.dto.CustomerDTO;
import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.domain.entity.Customer;
import br.com.ibm.bank.domain.enums.Branch;
import br.com.ibm.bank.repository.CustomerRepository;
import br.com.ibm.bank.service.account.IAccountService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ICustomerServiceTest {


    @Mock
    private CustomerRepository repository;

    @Mock
    private IAccountService accountService;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customerDTO = new CustomerDTO();
        customerDTO.setDocument("123456789");
        customerDTO.setName("John Doe");
        customerDTO.setEmail("john.doe@example.com");
        customerDTO.setAge(30);
    }

    @Test
    void testCreate_Success() throws ValidationException, jakarta.xml.bind.ValidationException {

        Customer mockCustomer = new Customer();
        mockCustomer.setDocument(customerDTO.getDocument());
        mockCustomer.setName(customerDTO.getName());
        mockCustomer.setEmail(customerDTO.getEmail());
        mockCustomer.setAge(customerDTO.getAge());
        mockCustomer.setCreateDate(LocalDate.now());
        mockCustomer.setUpdateDate(LocalDate.now());

        Account mockAccount = new Account();
        mockAccount.setNumberAccount(789);
        mockAccount.setBranch(Branch.BRANCH_VILMARIANA_SAOPAULO.getValue());
        mockAccount.setBalance(0.0);

        when(repository.findByDocument(customerDTO.getDocument())).thenReturn(null);
        when(accountService.create("789")).thenReturn(mockAccount);
        when(repository.save(any(Customer.class))).thenReturn(mockCustomer);


        Customer result = customerService.create(customerDTO);

        assertNotNull(result);
        assertEquals(customerDTO.getDocument(), result.getDocument());
        assertEquals(customerDTO.getName(), result.getName());
        assertEquals(customerDTO.getEmail(), result.getEmail());
        assertEquals(customerDTO.getAge(), result.getAge());
        assertEquals(1, result.getAccounts().size());
        assertEquals(mockAccount, result.getAccounts().get(0));
        verify(repository, times(1)).findByDocument(customerDTO.getDocument());
        verify(accountService, times(1)).create("789");
        verify(repository, times(1)).save(any(Customer.class));
    }

    @Test
    void testCreate_AlreadyExists() {

        Customer existingCustomer = new Customer();
        existingCustomer.setDocument(customerDTO.getDocument());
        when(repository.findByDocument(customerDTO.getDocument())).thenReturn(existingCustomer);

        ValidationException exception = assertThrows(ValidationException.class, () -> customerService.create(customerDTO));
        assertEquals("customer alredy exist", exception.getMessage());
        verify(repository, times(1)).findByDocument(customerDTO.getDocument());
        verify(repository, never()).save(any(Customer.class));
        verify(accountService, never()).create(anyString());
    }

    @Test
    void testGetLastThreeDigits() {

        String result = customerService.getLastThreeDigits(customerDTO.getDocument());

        assertEquals("789", result);
    }
}