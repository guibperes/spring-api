package guizao.aula.api.purchaseorder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import guizao.aula.api.customer.Customer;
import guizao.aula.api.customer.CustomerService;
import guizao.aula.api.product.Product;
import guizao.aula.api.product.ProductService;
import guizao.aula.base.BaseService;

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
    Customer customer = customerService.listById(customerId);
    List<Product> listProducts = new ArrayList<Product>();
    for (String productId : products) {
      Product product = productService.listById(productId);
      product.setQuant(product.getQuant() - 1);
      listProducts.add(product);
    }
    for (Product product : listProducts) {
      productService.update(product);
    }
    PurchaseOrder order = purchaseOrderRepo.save(new PurchaseOrder(customer, listProducts));
    return order.getId();
  }
}
