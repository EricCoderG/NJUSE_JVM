package vjvm.interpreter.instruction.constants.comparions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LCMP extends Instruction {

  private final String name;

  public static LCMP LCMP(ProgramCounter pc, MethodInfo method) {
    return new LCMP("lcmp");
  }
  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    long value2 = opStack.popLong();
    long value1 = opStack.popLong();
    if (value1 > value2) {
      opStack.pushInt(1);
    }
    else if (value1 == value2) {
      opStack.pushInt(0);
    } else {
      opStack.pushInt(-1);
    }

  }

  @Override
  public String toString() {
    return name;
  }
}
