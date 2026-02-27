package com.adrar.cdafad.service;

import com.adrar.cdafad.dto.GameDTO;
import com.adrar.cdafad.dto.GameDTOWrapper;
import com.adrar.cdafad.entity.Game;
import com.adrar.cdafad.entity.Manufacturer;
import com.adrar.cdafad.exception.game.GameIsNotExistsByTitleException;
import com.adrar.cdafad.exception.game.GameIsNotExistsException;
import com.adrar.cdafad.exception.game.GameIsPresentException;
import com.adrar.cdafad.exception.game.GameListIsEmptyException;
import com.adrar.cdafad.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class GameService {

    private GameRepository gameRepository;

    //Méthodes
    //Méthode pour ajouter un Game en BDD
    public Game createGame(Game game)
    {
        //Si le jeu existe déja
        if (this.gameRepository.existsByTitle(game.getTitle())) {
            throw new GameIsPresentException(game.getTitle());
        }
        return gameRepository.save(game);
    }

    //Méthode qui retourne un Game par son ID
    public Game getGameById(Integer id)
    {
        Optional<Game> game = this.gameRepository.findById(id);
        //Si le jeu n'existe pas
        if (game.isEmpty()) {
            throw new GameIsNotExistsException(id);
        }
        return game.get();
    }

    //Méthode qui retourne la liste des Game (Iterable)
    public Iterable<Game> getAllGames()
    {
        //Si la liste des Game est vide
        if (this.gameRepository.count() == 0) {
            throw new GameListIsEmptyException();
        }
        return this.gameRepository.findAll();
    }

    public Stream<GameDTO> getGamesDTOBy(String title)
    {
        Optional<Game> game = this.gameRepository.findByTitle(title);
        //Test si le Game n'existe pas
        if (game.isEmpty()) {
            throw new GameIsNotExistsByTitleException(title);
        }
        return this.gameRepository
                .findByTitle(title)
                .stream()
                .map(
                GameDTOWrapper::wrapGameToGameDTO
            );
    }
}
