package kata_london_style.infrastructure.entry;

public final class Main
{
  private CommandExecutor commandExecutor;

  public Main(CommandExecutor commandExecutor)
  {
    this.commandExecutor = commandExecutor;
  }

  public void execute(String[] args)
  {
    commandExecutor.execute(args);
  }
}
