package kata_london_style;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import kata_london_style.domain.model.State;
import kata_london_style.infrastructure.entry.CommandExecutor;
import kata_london_style.infrastructure.entry.Main;

public class MainTest
{
  private static final String INVALID_COMMAND = "393kd9";
  private static final String USER = "mramos";
  private static final String USER_TO_FOLLOW = "kkk";
  private static final String USER_REGISTRATION = "userRegistration";
  private static final String FOLLOW_USER = "followUser";
  private Main main;

  private Mockery context;

  private CommandExecutor commandExecutor;

  @Before
  public void setUp()
  {
    context = new Mockery();
    context.setImposteriser(ClassImposteriser.INSTANCE);
    commandExecutor = context.mock(CommandExecutor.class);
    main = new Main(commandExecutor);
  }

  @Test
  public void mainWithCommandOk()
  {
    context.checking(new Expectations()
    {
      {
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
        oneOf(commandExecutor).execute(INVALID_COMMAND, USER);
        will(returnValue(State.KO));
      }
    });

    String[] args = { INVALID_COMMAND, USER};

    main.execute(args);

    context.assertIsSatisfied();
  }

  @Test
  public void mainWithCommandFollowUser()
  {
    context.checking(new Expectations()
    {
      {
        oneOf(commandExecutor).execute(FOLLOW_USER, USER, USER_TO_FOLLOW);
        will(returnValue(State.OK));
      }
    });

    String[] args = { FOLLOW_USER, USER, USER_TO_FOLLOW};

    main.execute(args);

    context.assertIsSatisfied();
  }
}
