package kata_london_style;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import kata_london_style.infrastructure.entry.UserRegistrationCommand;
import kata_london_style.infrastructure.repository.UserEntity;
import kata_london_style.infrastructure.repository.UserEntityAdapter;

public class UserRegistrationCommandTest
{
  private static final String NEW_USER = "mramos";
  private UserEntity newUserEntity = new UserEntity(NEW_USER);

  private Mockery context;
  private UserRegistrationCommand userRegistrationCommand;
  private UserEntityAdapter userEntityRepository;

  @Before
  public void setUp()
  {
    context = new Mockery();
    context.setImposteriser(ClassImposteriser.INSTANCE);
    userEntityRepository = context.mock(UserEntityAdapter.class);
    userRegistrationCommand = new UserRegistrationCommand(userEntityRepository);
  }

  @Test
  public void addNewUser()
  {
    context.checking(new Expectations()
    {
      {
        oneOf(userEntityRepository).addUser(newUserEntity);
      }
    });

    userRegistrationCommand.execute(NEW_USER);

    context.assertIsSatisfied();
  }
}
