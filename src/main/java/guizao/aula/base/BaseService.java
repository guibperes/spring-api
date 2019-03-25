package guizao.aula.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import guizao.aula.exception.EntityNotFoundException;
import guizao.aula.utils.Id;

public class BaseService<ENTITY extends BaseEntity, REPOSITORY extends JpaRepository<ENTITY, String>> {

  private String getEntityName () {
    Class<?> clazz = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    return clazz.getSimpleName();
  }

  @Autowired
  private REPOSITORY repo;

  public Id create (ENTITY entity) {
    ENTITY savedEntity = repo.save(entity);
    return new Id(savedEntity.getId());
  }

  public Id update (ENTITY entity) {
    if (repo.existsById(entity.getId())) {
      repo.save(entity);
      return new Id(entity.getId());
    }
    throw new EntityNotFoundException(this.getEntityName(), entity.getId());
  }

  public void delete (String id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
    } else {
      throw new EntityNotFoundException(this.getEntityName(), id);
    }
  }

  public List<ENTITY> listAll () {
    return repo.findAll();
  }

  public ENTITY listById (String id) {
    if (repo.existsById(id)) {
      return repo.findById(id).get();
    }
    throw new EntityNotFoundException(this.getEntityName(), id);
  }
}
