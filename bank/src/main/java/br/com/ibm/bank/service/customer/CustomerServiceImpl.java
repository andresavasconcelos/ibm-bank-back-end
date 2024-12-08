package br.com.ibm.bank.service.customer;

import br.com.ibm.bank.domain.dto.CustomerDTO;
import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.domain.entity.Customer;
import br.com.ibm.bank.repository.CustomerRepository;
import br.com.ibm.bank.service.account.IAccountService;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService{
    @Autowired
    private final CustomerRepository repository;
    @Autowired
    private final IAccountService accountService;

    public CustomerServiceImpl(CustomerRepository repository, IAccountService accountService){
        this.repository = repository;
        this.accountService = accountService;
    }

    @Override
    public Customer create(CustomerDTO customerDTO) throws ValidationException {

        Customer customer = new Customer();
        customer.setDocument(customerDTO.getDocument());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setAge(customerDTO.getAge());
        customer.setUpdateDate(LocalDate.now());
        customer.setCreateDate(LocalDate.now());

        customer = findCustomerByDocument(customer.getDocument());

        if(customer != null){
            Account account = accountService.create(getLastThreeDigits(customer.getDocument()));

            List<Account> accountList = new ArrayList<>();
            accountList.add(account);
            customer.setAccounts(accountList);

            return repository.save(customer);
        } else {
            throw new ValidationException("customer alredy exist", "1");
        }
    }

    String getLastThreeDigits(String document) {
        return document.substring(document.length() - 3);
    }

    private Customer findCustomerByDocument(String document){
        return repository.findByDocument(document);
    }
}
