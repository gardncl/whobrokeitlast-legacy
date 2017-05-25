package gardncl.whobrokeitlast.dao;

import gardncl.whobrokeitlast.models.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectDao extends CrudRepository<Project, Long> {
}
