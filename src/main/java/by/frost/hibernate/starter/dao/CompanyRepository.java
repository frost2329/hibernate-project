package by.frost.hibernate.starter.dao;

import by.frost.hibernate.starter.entity.Company;

import javax.persistence.EntityManager;

public class CompanyRepository extends BaseRepository<Integer, Company>{
    public CompanyRepository(EntityManager entityManager) {
        super(Company.class, entityManager);
    }
}
