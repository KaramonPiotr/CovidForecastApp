package backend.backend;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import backend.backend.AppModel;

public interface AppRepository extends CrudRepository<AppModel,String> {
    AppModel findAppModelByNameAndDay(String name,Double day);
    List<AppModel> findAppModelByName(String name);
    List<AppModel> findAllByOrderByName();
    Boolean existsByName(String name);
}