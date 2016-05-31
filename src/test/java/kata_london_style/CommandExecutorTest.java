package kata_london_style;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

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
    userRegistration = context.mock(UserRegistration.class);
  }

  @Test
  public void executeNewUserRegistration()
  {
    context.checking(new Expectations()
    {
      {
        oneOf(userRegistration).execute(USER);
        will(returnValue("OK"));
      }
    });
    commandExecutor = new CommandExecutor(userRegistration);

    commandExecutor.execute(NEW_USER_COMMAND, USER);
  }
}
