package com.llp.mam.dtos;

import com.llp.mam.entity.Department;
import com.llp.mam.entity.Sector;
import com.llp.mam.entity.User;
import com.llp.mam.enums.Profile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        @NotNull Long userId,
        @NotBlank String userName,
        @NotNull Long departmentId,
        @NotNull Long sectorId,
        @NotNull Profile profile
) {
    public User toEntity(Department department, Sector sector){
        User user = new User();
        user.setId(this.userId);
        user.setUserName(this.userName);
        user.setDepartment(department);
        user.setSector(sector);
        user.setProfile(this.profile);

        return user;
    }

    public static UserDto fromEntity(User user){
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getDepartment() .getId(),//!= null? user.getDepartment().getId():null,
                user.getSector().getId(), //!= null? user.getSector().getId():null,
                user.getProfile()
        );


    }

}
