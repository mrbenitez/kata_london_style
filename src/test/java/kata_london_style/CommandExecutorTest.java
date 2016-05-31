package kata_london_style;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import kata_london_style.domain.model.State;
import kata_london_style.domain.ports.primary.ContentProcessCommand;
import kata_london_style.infrastructure.entry.CommandExecutor;
import kata_london_style.infrastructure.entry.UserRegistrationCommand;

public class CommandExecutorTest
{
  private static final String USER = "mramos";
  private static final String USER_TO_FOLLOW = "kkk";
  private static final String USER_REGISTRATION_COMMAND = "userRegistration";
  private static final String FOLLOW_USER_COMMAND = "followUser";
  private CommandExecutor commandExecutor;
  private Mockery context;
  private ContentProcessCommand userRegistration;
  private ContentProcessCommand followUser;

  @Before
  public void setUp()
  {
    context = new Mockery();
    context.setImposteriser(ClassImposteriser.INSTANCE);
    userRegistration = context.mock(UserRegistrationCommand.class);
    followUser = context.mock(FollowUserCommand.class);
    commandExecutor = new CommandExecutor(userRegistration, followUser);
  }

  @Test
  public void executeUserRegistrationWhenUserIsNew()
  {
    context.checking(new Expectations()
    {
      {
        oneOf(userRegistration).execute(USER);
        will(returnValue(State.OK));
      }
    });

    State result = commandExecutor.execute(USER_REGISTRATION_COMMAND, USER);

    verify(result, State.OK);
  }

  @Test
  public void executeUserRegistrationWhenExistUser()
  {
    context.checking(new Expectations()
    {
      {
        oneOf(userRegistration).execute(USER);
        will(returnValue(State.KO));
      }
    });

    State result = commandExecutor.execute(USER_REGISTRATION_COMMAND, USER);

    verify(result, State.KO);
  }

  @Test
  public void executeFollowUser()
  {
    context.checking(new Expectations()
    {
      {
        oneOf(followUser).execute(USER, USER_TO_FOLLOW);
        will(returnValue(State.OK));
      }
    });

    State result = commandExecutor.execute(FOLLOW_USER_COMMAND, USER, USER_TO_FOLLOW);

    verify(result, State.OK);
  }

  private void verify(State result, State expected)
  {
    assertThat(result, equalTo(expected));
    context.assertIsSatisfied();
  }
}
