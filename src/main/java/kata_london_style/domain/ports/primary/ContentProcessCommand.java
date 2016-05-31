package kata_london_style.domain.ports.primary;

import java.util.List;

import kata_london_style.domain.model.State;

public interface ContentProcessCommand
{
  public State execute(List<String> args);
}
