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
import guizao.aula.exception.ProductAmountException;

@Service
public class PurchaseOrderService extends BaseService<PurchaseOrder, PurchaseOrderRepository> {

  @Autowired
  private PurchaseOrderRepository purchaseOrderRepo;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private ProductService productService;

  @Transactional(propagation = Propagation.REQUIRED)
  public String newOrder (String customerId, List<PurchaseOrderProductDTO> products) {
    Customer customer = customerService.listById(customerId);
    List<Product> list = new ArrayList<Product>();
    for (PurchaseOrderProductDTO productDTO : products) {
      Product product = productService.listById(productDTO.getId());
      if (product.getAmount() - productDTO.getAmount() >= 0) {
        product.setAmount(product.getAmount() - productDTO.getAmount());
        productService.update(product);
        list.add(product);
      } else {
        throw new ProductAmountException(product.getName());
      }
    }
    PurchaseOrder order = purchaseOrderRepo.save(new PurchaseOrder(customer, list));
    return order.getId();
  }
}
