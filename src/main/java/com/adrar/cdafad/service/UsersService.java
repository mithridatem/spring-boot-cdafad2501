package com.adrar.cdafad.service;

import com.adrar.cdafad.dto.UsersDTOWrapper;
import com.adrar.cdafad.dto.UsersInfoDTO;
import com.adrar.cdafad.entity.Users;
import com.adrar.cdafad.exception.users.UsersListIsEmptyException;
import com.adrar.cdafad.exception.users.UsersIsNotExistsException;
import com.adrar.cdafad.exception.users.UsersIsPresentException;
import com.adrar.cdafad.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UsersService {

    private UsersRepository usersRepository;

    //Ajouter un Users
    public Users createUsers(Users users) {

        //Si le compte déjà
        if (usersRepository.findByEmail(users.getEmail()).isPresent())
        {
            throw new UsersIsPresentException();
        }
        return usersRepository.save(users);
    }

    //Récupérer un Users par son email
    public Users getUsersByEmail(String email) {
        Optional<Users> users = usersRepository.findByEmail(email);
        if (users.isEmpty()) {
            throw new UsersIsNotExistsException();
        }
        return usersRepository.findByEmail(email).get();
    }

    //mise à jour de la collection de game d'un users
    public Users updateGamesCollectionToUsers(Users users)
    {
        Optional<Users> udpateUsers = this.usersRepository.findById(users.getId());
        if (udpateUsers.isEmpty()) {
            throw new UsersIsNotExistsException();
        }
        //test si le compte n'existe pas déja (gestion des doublons email)
        if (!udpateUsers.get().getEmail().equals(users.getEmail()) ) {
            Optional<Users> verifUsers = this.usersRepository.findByEmail(users.getEmail());
            if (verifUsers.isPresent()) {
                throw new UsersIsPresentException();
            }
        }

        return this.usersRepository.save(users);
    }

    //Récupérer la liste des Users
    public Iterable<Users> findAllUsers()
    {
        if (this.usersRepository.count() == 0) {
            throw new UsersListIsEmptyException();
        }
        return this.usersRepository.findAll();
    }

    public Stream<UsersInfoDTO> getUserDTOByEmail(String email)
    {
        if (this.usersRepository.findByEmail(email).isEmpty()) {
            throw new UsersIsNotExistsException();
        }
        return this.usersRepository
                .findByEmail(email)
                .stream()
                .map(
                UsersDTOWrapper::wrapUsersToUsersInfoDTO
            );
    }
}
