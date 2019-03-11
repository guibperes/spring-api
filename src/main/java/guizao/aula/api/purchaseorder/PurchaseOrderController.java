package guizao.aula.api.purchaseorder;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import guizao.aula.auth.role.annotation.RoleApiUser;
import guizao.aula.config.RestConfig;
import guizao.aula.exception.BadRequestException;
import guizao.aula.utils.Id;
import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

@RestConfig
@Api(tags = "Purchase Order")
@RequestMapping("/purchaseorder")
public class PurchaseOrderController {

  @Autowired
  private PurchaseOrderService purchaseOrderService;

  @RoleApiUser
  @PostMapping("/{customerId}")
  public ResponseEntity<Id> newOrder (@Valid @RequestBody List<String> products, @PathVariable String customerId, @ApiIgnore Errors err) {
    if (!err.hasErrors()) {
      String orderId = purchaseOrderService.newOrder(customerId, products);
      return new ResponseEntity<Id>(new Id(orderId), HttpStatus.OK);
    }
    throw new BadRequestException("PurchaseOrder", err);
  }

  @RoleApiUser
  @GetMapping
  public ResponseEntity<List<PurchaseOrder>> getAll () {
    return new ResponseEntity<List<PurchaseOrder>>(purchaseOrderService.listAll(), HttpStatus.OK);
  }
}
