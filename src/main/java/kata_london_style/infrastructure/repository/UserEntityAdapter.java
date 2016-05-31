package kata_london_style.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import kata_london_style.domain.ports.secundary.UserEntityRepository;

public class UserEntityAdapter implements UserEntityRepository
{
  private EntityManager entityManager;

  public UserEntityAdapter(EntityManager entityManager)
  {
    this.entityManager = entityManager;
  }

  @Override
  public void addUser(UserEntity user)
  {
    entityManager.persist(user);
  }

  @Override
  public Optional<UserEntity> findByName(String name)
  {
    try
    {
      return Optional.of(entityManager.find(UserEntity.class, name));
    }
    catch (IllegalArgumentException e)
    {
      return Optional.empty();
    }
  }
}
