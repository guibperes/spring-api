package guizao.aula.api.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class ApiUser extends BaseEntity {

  @NotBlank
  @Size(min = 4, max = 40)
  @Column(unique = true, updatable = false)
  private String username;

  @NotBlank
  @JsonProperty(access = Access.WRITE_ONLY)
  @Size(min = 6, max = 60)
  private String password;
}
