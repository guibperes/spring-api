package guizao.aula.api.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import guizao.aula.auth.UserAuth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiUser extends UserAuth {

  private static final long serialVersionUID = 1L;

  @NotBlank
  @Size(min = 4, max = 40)
  @Column(unique = true, updatable = false)
  private String login;
}
