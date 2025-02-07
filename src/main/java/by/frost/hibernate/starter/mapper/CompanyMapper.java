package by.frost.hibernate.starter.mapper;

import by.frost.hibernate.starter.dto.CompanyDto;
import by.frost.hibernate.starter.entity.Company;

public class CompanyMapper implements Mapper<Company, CompanyDto>{
    @Override
    public CompanyDto mapFrom(Company object) {
        return new CompanyDto(object.getId(), object.getName());
    }
}
