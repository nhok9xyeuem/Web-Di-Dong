package nhuconghaui.webdidong.controller;

import nhuconghaui.webdidong.model.Role;
import nhuconghaui.webdidong.model.User;
import nhuconghaui.webdidong.service.RoleService;
import nhuconghaui.webdidong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public ModelAndView listUser(){
        Iterable<User> users= userService.findAll();
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("users", users);
        return modelAndView;

    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new User());

        return "registry";
    }

    @PostMapping("/register")
    public ModelAndView register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String role){
        ModelAndView modelAndView = new ModelAndView("index");
        if (userService.findByName(email)==null){
            User user1 = new User();
            user1.setEmail(email);
            user1.setPassword(passwordEncoder.encode(password));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleService.findByName(role));
            user1.setRoles(roles);
            modelAndView.addObject("user",user1);
            userService.save(user1);
        }
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public String updateForm(Model model, @PathVariable Integer id){
        model.addAttribute("user",userService.findById(id)) ;
        return "user/update";
    }

    @PostMapping("/update")
    public String update(@RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String role,
                         @RequestParam Integer id){
        if (userService.findByName(email)==null) {
            User user = userService.findById(id);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleService.findByName(role));
            user.setRoles(roles);
            userService.save(user);
        }
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteForm(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "user/delete";
    }

    @PostMapping("delete")
    public String delete(@RequestParam Integer id){
        userService.deleteById(id);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
