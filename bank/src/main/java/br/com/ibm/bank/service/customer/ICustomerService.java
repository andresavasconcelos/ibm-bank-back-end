package br.com.ibm.bank.service.customer;

import br.com.ibm.bank.domain.entity.Customer;

public interface ICustomerService {

    void create(Customer newCustomer);

    void update(Customer updateCustomer);
}
