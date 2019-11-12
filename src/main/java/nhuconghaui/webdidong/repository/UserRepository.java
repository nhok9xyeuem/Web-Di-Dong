package nhuconghaui.webdidong.repository;

import nhuconghaui.webdidong.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findByEmail(String email);
}