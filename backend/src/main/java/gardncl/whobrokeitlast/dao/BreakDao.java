package gardncl.whobrokeitlast.dao;

import gardncl.whobrokeitlast.models.Break;
import org.springframework.data.repository.CrudRepository;

public interface BreakDao extends CrudRepository<Break, Long> {
}
