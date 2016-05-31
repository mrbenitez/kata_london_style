package kata_london_style;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import kata_london_style.infrastructure.entry.UserRegistrationCommand;

public class UserRegistrationCommandTest
{

  protected static final String NEW_USER = "mramos";
  private Mockery context;
  private UserRegistrationCommand userRegistrationCommand;
  private UserEntityRepository userEntityRepository;

  @Before
  public void setUp()
  {
    context = new Mockery();
    context.setImposteriser(ClassImposteriser.INSTANCE);
    userEntityRepository = context.mock(UserEntityRepository.class);

  }

  @Test
  public void addNewUser()
  {
    context.checking(new Expectations()
    {
      {
        oneOf(userEntityRepository).save(NEW_USER);
        will(returnValue(true));
      }
    });

    userRegistrationCommand.save(NEW_USER);

    context.assertIsSatisfied();
  }
}
