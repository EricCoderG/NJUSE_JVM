package vjvm.interpreter.instruction.control;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GOTO extends Instruction {

  private final int offset;

  private final String name;

  public static GOTO GOTO(ProgramCounter pc, MethodInfo method) {
    return new GOTO(pc.short_(), "goto");
  }
  @Override
  public void run(JThread thread) {
    var pc = thread.pc();
    pc.move(offset-3);
  }

  @Override
  public String toString() {
    return name;
  }
}
