package guizao.aula.log.login;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import guizao.aula.api.user.ApiUser;
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
public class LoginLog extends BaseEntity {

  @ManyToOne
  private ApiUser apiUser;

  private Boolean authorized;
}
