package com.ehb.javaadvanced.TerroristBE.persistence;

import com.ehb.javaadvanced.TerroristBE.domain.User;

/**************
 * Mapper from Entity to Domain
 */

public class UserMapper {

    public static User toDomain(UserEntity e){
        User user = new User();
        user.setId(e.getId());
        user.setUsername(e.getUsername());
        return user;
    }
}
