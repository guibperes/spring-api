package guizao.aula.api.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import guizao.aula.base.BaseController;
import guizao.aula.config.RestConfig;
import io.swagger.annotations.Api;

@RestConfig
@Api(tags = "Customer")
@RequestMapping("/customer")
public class CustomerController extends BaseController<Customer, CustomerRepository, CustomerService> {

  @Autowired
  private CustomerService service;

  @GetMapping(path = "/search")
  public Customer findByName (@RequestParam(value = "name", required = true) String name) {
    return service.findByName(name);
  }
}
