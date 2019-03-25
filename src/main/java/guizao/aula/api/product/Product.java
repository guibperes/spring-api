package guizao.aula.api.product;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class Product extends BaseEntity {

  @NotBlank
  @Size(min = 4, max = 40)
  private String name;

  @NotNull
  @Min(value = 0)
  private int amount;
}
