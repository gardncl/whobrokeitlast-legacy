package gardncl.whobrokeitlast.dao;

import gardncl.whobrokeitlast.models.Break;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BreakDao extends CrudRepository<Break, Long> {
    List<Break> findAllByProject_Id(Long id);
}
