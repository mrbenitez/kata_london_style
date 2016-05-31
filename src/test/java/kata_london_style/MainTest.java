package kata_london_style;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import kata_london_style.domain.model.State;
import kata_london_style.infrastructure.entry.CommandExecutor;
import kata_london_style.infrastructure.entry.CommandInterpreter;
import kata_london_style.infrastructure.entry.Main;

public class MainTest
{
  private static final String INVALID_COMMAND = "393kd9";
  private static final String USER = "mramos";
  private static final String USER_REGISTRATION = "userRegistration";
  private Main main = new Main();

  private Mockery context;
  private CommandInterpreter commandInterpreter;
  private CommandExecutor commandExecutor;

  @Before
  public void setUp()
  {
    context = new Mockery();
    context.setImposteriser(ClassImposteriser.INSTANCE);
    commandExecutor = context.mock(CommandExecutor.class);
    commandInterpreter = context.mock(CommandInterpreter.class);
    main.setCommandExecutor(commandExecutor);
    main.setCommandInterpreter(commandInterpreter);
  }

  @Test
  public void mainWithCommandOk()
  {
    context.checking(new Expectations()
    {
      {
        oneOf(commandInterpreter).valid(USER_REGISTRATION);
        will(returnValue(true));
        oneOf(commandExecutor).execute(USER_REGISTRATION, USER);
        will(returnValue(State.OK));
      }
    });

    String[] args = { USER_REGISTRATION, USER};

    main.execute(args);

    context.assertIsSatisfied();
  }

  @Test
  public void mainWithCommandKo()
  {
    context.checking(new Expectations()
    {
      {
        oneOf(commandInterpreter).valid(INVALID_COMMAND);
        will(returnValue(false));
        never(commandExecutor).execute(INVALID_COMMAND, USER);
      }
    });

    String[] args = { INVALID_COMMAND, USER};

    main.execute(args);

    context.assertIsSatisfied();
  }
}
