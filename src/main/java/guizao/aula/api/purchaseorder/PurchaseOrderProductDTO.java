package guizao.aula.api.purchaseorder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseOrderProductDTO {

  @NotBlank
  private String id;

  @NotNull
  @Min(value = 0)
  private int amount;
}
