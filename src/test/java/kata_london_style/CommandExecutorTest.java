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
  private static final String NEW_USER_COMMAND = "userRegistration";
  private CommandExecutor commandExecutor;
  private Mockery context;
  private ContentProcessCommand userRegistration;

  @Before
  public void setUp()
  {
    context = new Mockery();
    context.setImposteriser(ClassImposteriser.INSTANCE);
    userRegistration = context.mock(UserRegistrationCommand.class);
    commandExecutor = new CommandExecutor(userRegistration);
  }

  @Test
  public void executeWhenUserIsNew()
  {
    context.checking(new Expectations()
    {
      {
        oneOf(userRegistration).execute(USER);
        will(returnValue(State.OK));
      }
    });

    State result = commandExecutor.execute(NEW_USER_COMMAND, USER);

    verify(result, State.OK);
  }

  @Test
  public void executeWhenExistUser()
  {
    context.checking(new Expectations()
    {
      {
        oneOf(userRegistration).execute(USER);
        will(returnValue(State.KO));
      }
    });

    State result = commandExecutor.execute(NEW_USER_COMMAND, USER);

    verify(result, State.KO);
  }

  private void verify(State result, State expected)
  {
    assertThat(result, equalTo(expected));
    context.assertIsSatisfied();
  }
}
