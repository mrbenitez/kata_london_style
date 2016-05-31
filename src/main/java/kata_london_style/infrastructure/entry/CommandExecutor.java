package kata_london_style.infrastructure.entry;

import java.util.ArrayList;
import java.util.List;

import kata_london_style.domain.model.State;
import kata_london_style.domain.ports.primary.ContentProcessCommand;

public class CommandExecutor
{
  private CommandInterpreter commandInterpreter;

  public CommandExecutor(CommandInterpreter commandInterpreter)
  {
    this.commandInterpreter = commandInterpreter;
  }

  public State execute(String... args)
  {
    if (commandInterpreter.valid(args[0]))
    {
      ContentProcessCommand contentProcessCommand = commandInterpreter.obtainCommand(args[0]);
      return contentProcessCommand.execute(obtainArguments(args));
    }

    return State.KO;
  }

  private List<String> obtainArguments(String... args)
  {

    List<String> arguments = new ArrayList<>();
    for (int i = 1; i < args.length; i++)
    {
      arguments.add(args[i]);
    }
    return arguments;
  }
}
