package kata_london_style;

public final class Main
{
  private ICommandExecutor commandExecutor;

  public void setCommandExecutor(ICommandExecutor commandExecutor)
  {
    this.commandExecutor = commandExecutor;
  }

  public void execute(String[] args)
  {
    commandExecutor.execute(args[0], args[1]);
  }
}
