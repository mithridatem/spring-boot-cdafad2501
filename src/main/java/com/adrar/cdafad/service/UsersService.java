package com.adrar.cdafad.service;

import com.adrar.cdafad.dto.UsersDTOWrapper;
import com.adrar.cdafad.dto.UsersInfoDTO;
import com.adrar.cdafad.entity.Role;
import com.adrar.cdafad.entity.Users;
import com.adrar.cdafad.exception.users.UsersListIsEmptyException;
import com.adrar.cdafad.exception.users.UsersIsNotExistsException;
import com.adrar.cdafad.exception.users.UsersIsPresentException;
import com.adrar.cdafad.repository.RoleRepository;
import com.adrar.cdafad.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UsersService {

    private UsersRepository usersRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    //Ajouter un Users
    public Users createUsers(Users users) {

        //Si le compte déjà
        if (usersRepository.findByUsername(users.getUsername()).isPresent())
        {
            throw new UsersIsPresentException();
        }
        return usersRepository.save(users);
    }

    //Récupérer un Users par son username
    public Users getUsersByEmail(String email) {
        Optional<Users> users = usersRepository.findByUsername(email);
        if (users.isEmpty()) {
            throw new UsersIsNotExistsException();
        }
        return usersRepository.findByUsername(email).get();
    }

    //mise à jour de la collection de game d'un users
    public Users updateGamesCollectionToUsers(Users users)
    {
        Optional<Users> udpateUsers = this.usersRepository.findById(users.getId());
        if (udpateUsers.isEmpty()) {
            throw new UsersIsNotExistsException();
        }
        //test si le compte n'existe pas déja (gestion des doublons username)
        if (!udpateUsers.get().getUsername().equals(users.getUsername()) ) {
            Optional<Users> verifUsers = this.usersRepository.findByUsername(users.getUsername());
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
        if (this.usersRepository.findByUsername(email).isEmpty()) {
            throw new UsersIsNotExistsException();
        }
        return this.usersRepository
                .findByUsername(email)
                .stream()
                .map(
                UsersDTOWrapper::wrapUsersToUsersInfoDTO
            );
    }

    public Users registerUser(Users users)
    {
        Role role = this.roleRepository.findByName("ROLE_USER").orElse(null);

        if (this.usersRepository.findByUsername(users.getUsername()).isPresent()) {
            throw new UsersIsPresentException();
        }
        users.getRoles().add(role);
        users.setPasswordHash(encoder.encode(users.getPasswordHash()));
        return this.usersRepository.save(users);
    }
}
