package br.com.ibm.bank.service.customer;

import br.com.ibm.bank.domain.entity.Customer;
import br.com.ibm.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceImpl implements ICustomerService{

    @Autowired
    private CustomerRepository repository;

    @Override
    public void create(Customer newCustomer) {
        repository.save(newCustomer);
    }

    @Override
    public void update(Customer updateCustomer) {
        repository.save(updateCustomer);
    }
}
