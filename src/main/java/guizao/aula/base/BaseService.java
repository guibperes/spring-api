package guizao.aula.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class BaseService<ENTITY extends BaseEntity, REPOSITORY extends JpaRepository<ENTITY, String>> {

  @Autowired
  private REPOSITORY repo;

  public Optional<String> create (ENTITY entity) {
    ENTITY savedEntity = repo.save(entity);
    return Optional.of(savedEntity.getId());
  }

  public Optional<String> update (ENTITY entity) {
    if (repo.existsById(entity.getId())) {
      repo.save(entity);
      return Optional.of(entity.getId());
    }
    return Optional.empty();
  }

  public Optional<Boolean> delete (String id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return Optional.of(true);
    }
    return Optional.empty();
  }

  public Optional<List<ENTITY>> listAll () {
    return Optional.of(repo.findAll());
  }

  public Optional<ENTITY> listById (String id) {
    if (repo.existsById(id)) {
      return Optional.of(repo.findById(id).get());
    }
    return Optional.empty();
  }
}
