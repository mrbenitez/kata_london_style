package kata_london_style.domain.ports.secundary;

import java.util.Optional;

import kata_london_style.infrastructure.repository.UserEntity;

public interface UserEntityRepository
{
  public Optional<UserEntity> findByName(String name);

  public void addUser(UserEntity user);
}
