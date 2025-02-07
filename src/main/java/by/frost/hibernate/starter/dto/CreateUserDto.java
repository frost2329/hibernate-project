package by.frost.hibernate.starter.dto;

import by.frost.hibernate.starter.entity.PersonalInfo;
import by.frost.hibernate.starter.entity.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
public record CreateUserDto(@Valid
                            PersonalInfo personalInfo,
                            @NotNull
                            String username,
                            Role role,
                            Integer companyId) {
}
