package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entities.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<User> all=userService.findAll();
        if(all!=null&&!all.isEmpty())return new ResponseEntity<>(all,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userService.saveUser(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String username){
        User updateUser=userService.getByUserName(username);
        if(updateUser!=null) {
            updateUser.setUserName(user.getUserName());
            updateUser.setPassword(user.getPassword());
            userService.saveUser(updateUser);
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String username){
        User user=userService.getByUserName(username);
        if(user!=null)return new ResponseEntity<>(user,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
