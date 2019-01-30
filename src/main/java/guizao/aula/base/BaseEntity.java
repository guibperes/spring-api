package guizao.aula.base;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
public class BaseEntity {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Size(max = 36)
  @Setter
  private String id;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @JsonIgnore
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public LocalDateTime getCreatedAt () {
    return this.createdAt;
  }

  @JsonIgnore
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public LocalDateTime getUpdatedAt () {
    return this.updatedAt;
  }
}
