package guizao.aula.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import guizao.aula.utils.Id;

public class BaseService<ENTITY extends BaseEntity, REPOSITORY extends JpaRepository<ENTITY, String>> {

  @Autowired
  private REPOSITORY repo;

  public Optional<Id> create (ENTITY entity) {
    ENTITY savedEntity = repo.save(entity);
    return Optional.of(new Id(savedEntity.getId()));
  }

  public Optional<Id> update (ENTITY entity) {
    if (repo.existsById(entity.getId())) {
      repo.save(entity);
      return Optional.of(new Id(entity.getId()));
    }
    return Optional.empty();
  }

  public Boolean delete (String id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    }
    return false;
  }

  public List<ENTITY> listAll () {
    return repo.findAll();
  }

  public Optional<ENTITY> listById (String id) {
    if (repo.existsById(id)) {
      return Optional.of(repo.findById(id).get());
    }
    return Optional.empty();
  }
}
