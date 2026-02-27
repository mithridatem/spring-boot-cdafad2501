package com.adrar.cdafad.dto;

import com.adrar.cdafad.entity.Users;

public class UsersDTOWrapper {

    public static UsersInfoDTO wrapUsersToUsersInfoDTO(Users users)
    {
       return new UsersInfoDTO(
               users.getEmail(),
               users.getFirstname(),
               users.getLastname()
       );
    }
}
