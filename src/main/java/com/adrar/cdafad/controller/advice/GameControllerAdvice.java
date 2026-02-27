package com.adrar.cdafad.controller.advice;

import com.adrar.cdafad.exception.category.CategoryIsPresentException;
import com.adrar.cdafad.exception.game.GameIsNotExistsByTitleException;
import com.adrar.cdafad.exception.game.GameIsNotExistsException;
import com.adrar.cdafad.exception.game.GameIsPresentException;
import com.adrar.cdafad.exception.game.GameListIsEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GameControllerAdvice {

    @ExceptionHandler(GameIsNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> GameIsNotExists(GameIsNotExistsException e)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return errorMap;
    }

    @ExceptionHandler(GameIsNotExistsByTitleException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> GameByTitleIsNotExists(GameIsNotExistsByTitleException e)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return errorMap;
    }

    @ExceptionHandler(GameIsPresentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> GameIsPresent(GameIsPresentException e)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return errorMap;
    }

    @ExceptionHandler(GameListIsEmptyException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String, String> GameListIsEmpty(GameListIsEmptyException e)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return errorMap;
    }


}
