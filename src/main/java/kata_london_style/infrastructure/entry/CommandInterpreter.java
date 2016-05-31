package kata_london_style.infrastructure.entry;

import java.util.HashMap;
import java.util.Map;

import kata_london_style.domain.ports.primary.ContentProcessCommand;

public class CommandInterpreter
{
  private Map<String, ContentProcessCommand> map = new HashMap<>();

  public CommandInterpreter(ContentProcessCommand userRegistration, ContentProcessCommand followUser)
  {
    map.put("userRegistration", userRegistration);
    map.put("followUser", followUser);
  }

  public boolean valid(String name)
  {
    return map.containsKey(name);
  }

  public ContentProcessCommand obtainCommand(String name)
  {
    return map.get(name);
  }
}
