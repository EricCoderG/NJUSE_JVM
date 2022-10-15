package vjvm.runtime;

import lombok.Getter;
import vjvm.vm.VMContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JThread {

  // The stack of frames
  private final ArrayList<JFrame> frames = new ArrayList<>();

  @Getter
  private final VMContext context;

  public JThread(VMContext context) {
    this.context = context;
  }

  public JFrame top() {
    return empty() ? null : frames.get(frames.size() - 1);
  }

  public void pop() {
    frames.remove(frames.size() - 1);
  }

  public void push(JFrame frame) {
    frames.add(frame);
  }

  // Return the active pc, which is owned by the top-most frame
  public ProgramCounter pc() {
    return empty() ? null : top().pc();
  }

  public boolean empty() {
    return frames.isEmpty();
  }

  public List<JFrame> frames() {
    return Collections.unmodifiableList(frames);
  }
}
