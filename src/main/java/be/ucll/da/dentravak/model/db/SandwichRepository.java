package be.ucll.da.dentravak.model.db;

import be.ucll.da.dentravak.model.domain.Sandwich;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SandwichRepository extends CrudRepository<Sandwich, UUID> {

}
