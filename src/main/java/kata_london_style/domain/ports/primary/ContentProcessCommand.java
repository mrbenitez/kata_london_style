package kata_london_style.domain.ports.primary;

import kata_london_style.domain.model.State;

public interface ContentProcessCommand
{
  public State execute(String name);
}
