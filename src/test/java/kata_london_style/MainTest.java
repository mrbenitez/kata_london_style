package kata_london_style;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

public class MainTest
{
  private Main main = new Main();

  @Test
  public void mainWithCommandOk()
  {
    Mockery context = new Mockery();
    context.setImposteriser(ClassImposteriser.INSTANCE);
    final ICommandExecutor commandExecutor = context.mock(CommandExecutor.class);
    context.checking(new Expectations()
    {
      {
        oneOf(commandExecutor).execute("userRegistration", "mramos");
        will(returnValue("OK"));
      }
    });

    main.setCommandExecutor(commandExecutor);

    String[] args = { "userRegistration", "mramos"};

    main.execute(args);

    context.assertIsSatisfied();
  }
}
