package kata_london_style.infrastructure.entry;

import java.util.HashMap;
import java.util.Map;

public class CommandInterpreter
{
  Map<String, String> map = new HashMap<>();

  public CommandInterpreter()
  {
    map.put("userRegistration", "OK");
  }

  public boolean valid(String command)
  {
    return map.containsKey(command);
  }
}
