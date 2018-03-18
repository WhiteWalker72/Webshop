package domain;

public interface DTOMapper<T, U> {

    U toDTO(T domainObject);

    T toDomainObject(U dto);

}
