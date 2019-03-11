package guizao.aula.api.purchaseorder;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import guizao.aula.api.customer.Customer;
import guizao.aula.api.product.Product;
import guizao.aula.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseOrder extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToMany
  private List<Product> products;
}
