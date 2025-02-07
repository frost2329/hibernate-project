package by.frost.hibernate.starter.dto;

import by.frost.hibernate.starter.entity.PersonalInfo;
import by.frost.hibernate.starter.entity.Role;

public record UserDto (Integer id,
                       PersonalInfo personalInfo,
                       String userName,
                       Role role,
                       CompanyDto company
)   {
}
