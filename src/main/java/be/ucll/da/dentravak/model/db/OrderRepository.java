package be.ucll.da.dentravak.model.db;


import be.ucll.da.dentravak.model.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static javafx.scene.input.KeyCode.T;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {

}