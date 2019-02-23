package guizao.aula.utils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Login {

  @NotBlank
  @Size(min = 4, max = 40)
  private String username;

  @NotBlank
  @Size(min = 6, max = 60)
  private String password;
}
