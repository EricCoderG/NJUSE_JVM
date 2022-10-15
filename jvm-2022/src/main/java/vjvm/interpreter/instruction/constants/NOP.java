package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NOP extends Instruction {
  private final String name;

  public static final NOP NOP(ProgramCounter pc, MethodInfo method) {
    return new NOP( "nop");
  }
  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
  }

  @Override
  public String toString() {
    return name;
  }
}
