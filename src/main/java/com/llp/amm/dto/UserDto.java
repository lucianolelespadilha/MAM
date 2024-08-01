package com.llp.amm.dto;

import com.llp.amm.entity.Department;
import com.llp.amm.entity.Sector;
import com.llp.amm.entity.User;
import com.llp.amm.enums.Profile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        @NotBlank String userName,
        @NotNull Long departmentId,
        @NotNull Long sectorId,
        @NotNull Profile profile
) {
    public User toEntity(Department department, Sector sector){
        User user = new User();
        user.setUserName(this.userName);
        user.setDepartment(department);
        user.setSector(sector);
        user.setProfile(this.profile);

        return user;
    }

    public static UserDto fromEntity(User user){
        return new UserDto(
                user.getUserName(),
                user.getDepartment() .getId(),//!= null? user.getDepartment().getId():null,
                user.getSector().getId(), //!= null? user.getSector().getId():null,
                user.getProfile()
        );


    }

}
