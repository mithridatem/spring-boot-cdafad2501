package com.adrar.cdafad.controller;

import com.adrar.cdafad.entity.Users;
import com.adrar.cdafad.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UsersService usersService;

    @PostMapping("/users")
    public ResponseEntity<Users> addUser(@RequestBody Users user)
    {
        return new ResponseEntity<>(
                this.usersService.createUsers(user),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<Users> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(
                this.usersService.getUsersByEmail(email),
                HttpStatus.OK
        );
    }

    @PatchMapping("/users")
    public ResponseEntity<Users> updateListGamesByUsers(@RequestBody Users users) {
        return new ResponseEntity<>(
                this.usersService.updateGamesCollectionToUsers(users),
                HttpStatus.OK
        );
    }
}
