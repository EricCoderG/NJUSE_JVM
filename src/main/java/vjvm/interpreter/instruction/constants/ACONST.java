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
public class ACONST extends Instruction {
  private String name;

  public static final ACONST ACONST_NULL(ProgramCounter pc, MethodInfo method) {
    return new ACONST("aconst_null");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
  }

  @Override
  public String toString() {
    return name;
  }
}
