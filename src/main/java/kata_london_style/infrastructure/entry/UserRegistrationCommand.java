package kata_london_style.infrastructure.entry;

import kata_london_style.model.ports.primary.ContentProcessCommand;

public class UserRegistrationCommand implements ContentProcessCommand
{
  @Override
  public String execute(String name)
  {
    return "OK";
  }
}
