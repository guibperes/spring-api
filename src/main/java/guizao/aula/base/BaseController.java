package guizao.aula.base;

import java.util.List;

import javax.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import guizao.aula.auth.role.annotation.RoleApiUser;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public class BaseController<ENTITY extends BaseEntity, REPOSITORY extends JpaRepository<ENTITY, String>, SERVICE extends BaseService<ENTITY, REPOSITORY>> {

  @Autowired
  private SERVICE service;

  @RoleApiUser
  @PostMapping
  public ResponseEntity<String> post (@Valid @RequestBody ENTITY entity, @ApiIgnore Errors errors) {
    if (!errors.hasErrors()) {
      Optional<String> id = service.create(entity);
      return new ResponseEntity<>(id.get(), HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @RoleApiUser
  @PutMapping
  public ResponseEntity<String> put (@Valid @RequestBody ENTITY entity, @ApiIgnore Errors errors) {
    if (!errors.hasErrors()) {
      Optional<String> id = service.update(entity);
      if (id.isPresent()) {
        return new ResponseEntity<>(id.get(), HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @RoleApiUser
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete (@PathVariable String id) {
    Optional<Boolean> deleted = service.delete(id);
    if (deleted.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    if (response.isPresent()) {
      return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
