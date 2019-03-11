package guizao.aula.api.purchaseorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import guizao.aula.api.customer.Customer;
import guizao.aula.api.customer.CustomerService;
import guizao.aula.api.product.Product;
import guizao.aula.api.product.ProductService;
import guizao.aula.base.BaseService;
import guizao.aula.exception.EntityNotFoundException;

@Service
public class PurchaseOrderService extends BaseService<PurchaseOrder, PurchaseOrderRepository> {

  @Autowired
  private PurchaseOrderRepository purchaseOrderRepo;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private ProductService productService;

  @Transactional(propagation = Propagation.REQUIRED)
  public String newOrder (String customerId, List<String> products) {
    Optional<Customer> customer = customerService.listById(customerId);
    List<Product> listProducts = new ArrayList<Product>();
    if (customer.isPresent()) {
      for (String productId : products) {
        Optional<Product> product = productService.listById(productId);
        if (product.isPresent()) {
          product.get().setQuant(product.get().getQuant() - 1);
          listProducts.add(product.get());
        } else {
          throw new EntityNotFoundException("Product", productId);
        }
      }
      for (Product product : listProducts) {
        productService.update(product);
      }
      PurchaseOrder order = purchaseOrderRepo.save(new PurchaseOrder(customer.get(), listProducts));
      return order.getId();
    }
    throw new EntityNotFoundException("Customer", customerId);
  }
}
