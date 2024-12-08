package br.com.ibm.bank.service.customer;

import br.com.ibm.bank.domain.dto.CustomerDTO;
import br.com.ibm.bank.domain.entity.Customer;

public interface ICustomerService {

    Customer create(CustomerDTO newCustomer);
}
