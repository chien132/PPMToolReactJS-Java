package chien.learn.PPMT.repositories;

import chien.learn.PPMT.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project,Long> {
    @Override
    Iterable<Project> findAllById(Iterable<Long> longs);
}
