package kata_london_style.infrastructure.entry;

import kata_london_style.domain.ports.primary.ContentProcessCommand;

public class CommandExecutor
{
  private ContentProcessCommand userRegistration;

  public CommandExecutor(ContentProcessCommand userRegistration)
  {
    this.userRegistration = userRegistration;
  }

  public String execute(String command, String arg)
  {
    return userRegistration.execute(arg);
  }
}
