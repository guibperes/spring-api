package guizao.aula.api.customer;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class Customer extends BaseEntity {

  @NotBlank
  @Size(min = 4, max = 40)
  private String name;

  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birth;
}
