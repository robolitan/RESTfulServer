package com.springboot.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.server.model.Role;
import com.springboot.server.model.SearchCriteria;
import com.springboot.server.model.User;
import com.springboot.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/admin")
public class RestController {

    @Autowired
    UserService userService;

    @GetMapping("user")
    public ResponseEntity users() {
        List<User> list = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(list);
    }

    @PostMapping("create")
    public String add(@RequestBody User user) throws JsonProcessingException {
        userService.addUser(user);
        return  new ObjectMapper().writeValueAsString(userService.getUserByLogin(user.getLogin()));
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Integer id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(id);
    }

    @GetMapping("{id}")
    public String user(@PathVariable("id") Integer id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(userService.getUser(id));
    }

    @PostMapping("edit")
    public String edit(@RequestBody SearchCriteria criteria) throws JsonProcessingException {
        User user = getUserParseCriteria(criteria);
        userService.editUser(user);
        return new ObjectMapper().writeValueAsString(user);
    }

    @GetMapping("user/{login}")
    public String getUserByLogin(@PathVariable("login") String login) throws JsonProcessingException {
        User user = userService.getUserByLogin(login);
        return new ObjectMapper().writeValueAsString(user);
    }

    private User getUserParseCriteria(SearchCriteria criteria) {
        User user = new User();
        user.setId(criteria.getId());
        user.setLogin(criteria.getLogin());
        user.setFirstName(criteria.getFirstName());
        user.setLastName(criteria.getLastName());
        user.setBirthday(criteria.getBirthday());
        user.setPassword(criteria.getPassword());
        Set<Role> roles = criteria.getRoles();
        Role roleAdmin = new Role(2, "ROLE_ADMIN");
        boolean roleAdminPresent = roles.stream().anyMatch(n -> n.getName().equals("ROLE_ADMIN"));
        if (criteria.getIsAdmin().equals("0")) {
            if (roleAdminPresent) {
                roles.remove(roleAdmin);
            }
        } else if (criteria.getIsAdmin().equals("1")) {
            if (!roleAdminPresent) {
                roles.add(roleAdmin);
            }
        }
        user.setRoles(roles);
        return user;
    }
}
