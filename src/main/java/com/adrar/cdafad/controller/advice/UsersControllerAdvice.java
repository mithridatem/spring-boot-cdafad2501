package com.adrar.cdafad.controller.advice;

import com.adrar.cdafad.exception.game.GameIsNotExistsException;
import com.adrar.cdafad.exception.game.GameIsPresentException;
import com.adrar.cdafad.exception.game.GameListIsEmptyException;
import com.adrar.cdafad.exception.users.UsersIsNotExistsException;
import com.adrar.cdafad.exception.users.UsersIsPresentException;
import com.adrar.cdafad.exception.users.UsersListIsEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UsersControllerAdvice {

    @ExceptionHandler(UsersIsNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> usersIsNotExists(UsersIsNotExistsException e)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return errorMap;
    }

    @ExceptionHandler(UsersIsPresentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> usersIsPresent(UsersIsPresentException e)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return errorMap;
    }

    @ExceptionHandler(UsersListIsEmptyException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String, String> usersListIsEmpty(UsersListIsEmptyException e)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return errorMap;
    }
}
