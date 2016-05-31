package kata_london_style.infrastructure.entry;

import javax.persistence.EntityExistsException;

import kata_london_style.domain.model.State;
import kata_london_style.domain.ports.primary.ContentProcessCommand;
import kata_london_style.infrastructure.repository.UserEntity;
import kata_london_style.infrastructure.repository.UserEntityAdapter;

public class UserRegistrationCommand implements ContentProcessCommand
{
  private UserEntityAdapter userEntityRepository;

  public UserRegistrationCommand(UserEntityAdapter userEntityRepository)
  {
    this.userEntityRepository = userEntityRepository;
  }

  @Override
  public State execute(String name)
  {
    UserEntity userEntity = new UserEntity(name);

    try
    {
      userEntityRepository.addUser(userEntity);
    }
    catch (EntityExistsException e)
    {
      return State.KO;
    }
    return State.OK;
  }
}
