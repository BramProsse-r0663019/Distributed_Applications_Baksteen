package be.ucll.da.dentravak.model.db;

import be.ucll.da.dentravak.model.domain.Sandwich;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LunchRepository extends CrudRepository<Sandwich, Long> {

}
