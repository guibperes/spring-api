package guizao.aula.base;

import java.util.List;

import javax.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import guizao.aula.auth.role.annotation.RoleApiUser;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public class BaseController<ENTITY extends BaseEntity, REPOSITORY extends JpaRepository<ENTITY, String>, SERVICE extends BaseService<ENTITY, REPOSITORY>> {

  @Autowired
  private SERVICE service;

  @RoleApiUser
  @PostMapping
  public ResponseEntity<String> post (@Valid @RequestBody ENTITY entity) {
    Optional<String> id = service.create(entity);
    return new ResponseEntity<>(id.get(), HttpStatus.CREATED);
  }

  @RoleApiUser
  @PutMapping
  public ResponseEntity<String> put (@Valid @RequestBody ENTITY entity) {
    Optional<String> id = service.update(entity);
    return new ResponseEntity<String>(id.get(), HttpStatus.OK);
  }

  @RoleApiUser
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete (@PathVariable String id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RoleApiUser
  @GetMapping
  public ResponseEntity<List<ENTITY>> getAll() {
    return new ResponseEntity<>(service.listAll().get(), HttpStatus.OK);
  }

  @RoleApiUser
  @GetMapping("/{id}")
  public ResponseEntity<ENTITY> getById (@PathVariable String id) {
    Optional<ENTITY> response = service.listById(id);
    return new ResponseEntity<>(response.get(), HttpStatus.OK);
  }
}
