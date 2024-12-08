package br.com.ibm.bank.service.customer;

import br.com.ibm.bank.domain.dto.CustomerDTO;
import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.domain.entity.Customer;
import br.com.ibm.bank.repository.CustomerRepository;
import br.com.ibm.bank.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService{
    private final CustomerRepository repository;
    private final IAccountService accountService;

    public CustomerServiceImpl(CustomerRepository repository, IAccountService accountService){
        this.repository = repository;
        this.accountService = accountService;
    }

    @Override
    public Customer create(CustomerDTO customerDTO) {

        Customer customer = new Customer();

        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setAge(customerDTO.getAge());
        customer.setUpdateDate(LocalDate.now());
        customer.setCreateDate(LocalDate.now());

        Account account = accountService.create();

        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        customer.setAccounts(accountList);

        return repository.save(customer);
    }

    private Customer findCustomerById(Integer id){
        return repository.findById(id).orElse(null);
    }
}
