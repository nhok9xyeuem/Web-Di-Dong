package nhuconghaui.webdidong.service;

import nhuconghaui.webdidong.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByName(String name);

    void save(User user);

    void deleteById(Long id);

    User findById(Long id);
}
