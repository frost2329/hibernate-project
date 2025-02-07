package by.frost.hibernate.starter.mapper;

import by.frost.hibernate.starter.dao.CompanyRepository;
import by.frost.hibernate.starter.dto.CreateUserDto;
import by.frost.hibernate.starter.entity.Company;
import by.frost.hibernate.starter.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    private final CompanyRepository companyRepository;
    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                        .personalInfo(object.personalInfo())
                .username(object.username())
                .role(object.role())
                .company(companyRepository.findById(object.companyId()).orElse(null))
                .build();
    }
}
