package nhuconghaui.webdidong.service.impl;

import nhuconghaui.webdidong.model.User;
import nhuconghaui.webdidong.repository.UserRepository;
import nhuconghaui.webdidong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByEmail(name);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }
}
