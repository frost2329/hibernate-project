package by.frost.hibernate.starter.mapper;

public interface Mapper<F, T> {
    T mapFrom(F object);
}
