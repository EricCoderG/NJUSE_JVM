package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class POPX extends Instruction {

  private final String name;

  public static POPX POP(ProgramCounter pc, MethodInfo method) {
    return new POPX("pop");
  }

  public static POPX POP2(ProgramCounter pc, MethodInfo method) {
    return new POPX("pop2");
  }
  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    int origin_top = opStack.top();
    int capacity = (opStack.slots().types()[origin_top] == Long.class || opStack.slots().types()[origin_top] == Double.class) ? 2 : 1;
    if (name.equals("pop") && capacity == 1) {
      opStack.popSlots(1);
    }
    else if (name.equals("pop2")){
      opStack.popSlots(1);
      if (origin_top - opStack.top() == 1) {
        opStack.popSlots(1);
      }
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
