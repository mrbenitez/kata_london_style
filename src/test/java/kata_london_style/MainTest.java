package kata_london_style;

import org.jmock.*;
import org.jmock.Mockery;
import org.jmock.Mockery.*;
import org.junit.Before;
import org.junit.Test;

public class MainTest
{
  private Mockery context = new Mockery();
  private CommandExecutor commandExecutor;
  private Main main = new Main();

  @Before
  public void setUp()
  {
    commandExecutor = context.mock(CommandExecutor.class);
    
    context.checking(new Expectations() {
      oneOf(commandExecutor).execute("userRegistration", "mramos");
    });
    
    main.setCommandExecutor(commandExecutor) ;
  }

  @Test
  public void main()
  {
    String[] args = { "userRegistration", "mramos"};

    main.execute(args);

    context.assertIsSatisfied();
  }
}
