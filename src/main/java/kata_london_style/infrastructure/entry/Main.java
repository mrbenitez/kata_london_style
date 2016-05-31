package kata_london_style.infrastructure.entry;

public final class Main
{
  private CommandExecutor commandExecutor;
  private CommandInterpreter commandInterpreter;

  public void execute(String[] args)
  {
    if (commandInterpreter.valid(args[0]))
    {
      commandExecutor.execute(args[0], args[1]);
    }
  }

  public void setCommandExecutor(CommandExecutor commandExecutor)
  {
    this.commandExecutor = commandExecutor;
  }

  public void setCommandInterpreter(CommandInterpreter commandInterpreter)
  {
    this.commandInterpreter = commandInterpreter;
  }
}
