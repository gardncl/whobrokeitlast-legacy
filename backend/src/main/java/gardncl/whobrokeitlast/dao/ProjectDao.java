package gardncl.whobrokeitlast.dao;

import gardncl.whobrokeitlast.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectDao extends PagingAndSortingRepository<Project, Long> {

}
