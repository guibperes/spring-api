package guizao.aula.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import guizao.aula.auth.role.annotation.RoleApiUser;
import guizao.aula.exception.BadRequestException;
import guizao.aula.utils.Id;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public class BaseController<ENTITY extends BaseEntity, REPOSITORY extends JpaRepository<ENTITY, String>, SERVICE extends BaseService<ENTITY, REPOSITORY>> {

  private String getEntityName () {
    Class<?> clazz = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    return clazz.getSimpleName();
  }

  @Autowired
  private SERVICE service;

  @RoleApiUser
  @PostMapping
  public ResponseEntity<Id> post (@Valid @RequestBody ENTITY entity, @ApiIgnore Errors err) {
    if (!err.hasErrors()) {
      Id id = service.create(entity);
      return new ResponseEntity<Id>(id, HttpStatus.CREATED);
    }
    throw new BadRequestException(this.getEntityName(), err);
  }

  @RoleApiUser
  @PutMapping
  public ResponseEntity<Id> put (@Valid @RequestBody ENTITY entity, @ApiIgnore Errors err) {
    if (!err.hasErrors()) {
      Id id = service.update(entity);
      return new ResponseEntity<Id>(id, HttpStatus.OK);
    }
    throw new BadRequestException(this.getEntityName(), err);
  }

  @RoleApiUser
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete (@PathVariable String id) {
    service.delete(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @RoleApiUser
  @GetMapping
  public ResponseEntity<List<ENTITY>> getAll() {
    return new ResponseEntity<List<ENTITY>>(service.listAll(), HttpStatus.OK);
  }

  @RoleApiUser
  @GetMapping("/{id}")
  public ResponseEntity<ENTITY> getById (@PathVariable String id) {
    ENTITY entity = service.listById(id);
    return new ResponseEntity<ENTITY>(entity, HttpStatus.OK);
  }
}
