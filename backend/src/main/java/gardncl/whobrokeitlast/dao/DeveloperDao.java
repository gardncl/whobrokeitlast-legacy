package gardncl.whobrokeitlast.dao;

import gardncl.whobrokeitlast.models.Developer;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperDao extends CrudRepository<Developer, Long> {

    Developer getByUserName(String username);
}
