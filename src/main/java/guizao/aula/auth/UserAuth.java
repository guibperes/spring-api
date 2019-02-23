package guizao.aula.auth;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import guizao.aula.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = { "roles" }, allowGetters = true)
public class UserAuth extends BaseEntity {

  @JsonIgnore
  @Column(unique = true)
  private String token;

  @NotBlank
  @JsonProperty(access = Access.WRITE_ONLY)
  @Size(min = 6, max = 60)
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  @ApiModelProperty(hidden = true)
  private Set<String> roles = new HashSet<>();
}
