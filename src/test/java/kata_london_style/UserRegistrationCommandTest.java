package kata_london_style;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityExistsException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import kata_london_style.domain.model.State;
import kata_london_style.infrastructure.entry.UserRegistrationCommand;
import kata_london_style.infrastructure.repository.UserEntity;
import kata_london_style.infrastructure.repository.UserEntityAdapter;

public class UserRegistrationCommandTest
{
  private static final String NEW_USER = "mramos";
  private UserEntity newUserEntity = new UserEntity(NEW_USER);
  private static final String OLD_USER = "asdfadf";
  private UserEntity oldUserEntity = new UserEntity(OLD_USER);

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

    State result = userRegistrationCommand.execute(NEW_USER);

    verify(result, State.OK);
  }

  @Test()
  public void addOldUserThenException()
  {
    context.checking(new Expectations()
    {
      {
        oneOf(userEntityRepository).addUser(oldUserEntity);
        will(throwException(new EntityExistsException()));
      }
    });

    State result = userRegistrationCommand.execute(OLD_USER);

    verify(result, State.KO);
  }

  private void verify(State result, State expected)
  {
    assertThat(result, equalTo(expected));
    context.assertIsSatisfied();
  }
}
