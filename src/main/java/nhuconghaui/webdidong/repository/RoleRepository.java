package nhuconghaui.webdidong.repository;

import nhuconghaui.webdidong.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);

}
