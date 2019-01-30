package guizao.aula.api.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guizao.aula.base.BaseService;

@Service
public class CustomerService extends BaseService<Customer, CustomerRepository> {

  @Autowired
  private CustomerRepository repo;

  public Customer findByName (String name) {
    return repo.findByName(name);
  }
}
