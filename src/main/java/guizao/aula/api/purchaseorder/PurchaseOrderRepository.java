package guizao.aula.api.purchaseorder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, String> {
}
