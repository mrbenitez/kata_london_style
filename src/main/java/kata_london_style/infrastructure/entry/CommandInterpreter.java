package kata_london_style.infrastructure.entry;

import java.util.HashMap;
import java.util.Map;

import kata_london_style.domain.model.State;

public class CommandInterpreter
{
  Map<String, State> map = new HashMap<>();

  public CommandInterpreter()
  {
    map.put("userRegistration", State.OK);
    map.put("followUser", State.OK);
  }

  public boolean valid(String command)
  {
    return map.containsKey(command);
  }
}
