package com.adrar.cdafad.dto;

import com.adrar.cdafad.entity.Game;

public class GameDTOWrapper {

    public static GameDTO wrapGameToGameDTO(Game game)
    {
        return new GameDTO(
                game.getTitle(),
                game.getDescription(),
                String.valueOf(game.getPublishAt().getYear()).toString(),
                game.getManufacturer().getName(),
                game.getManufacturer().getConsole()
        );
    }
}
