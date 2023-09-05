package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DUPX extends Instruction {

  private final String name;

  public static DUPX DUP(ProgramCounter pc, MethodInfo method) {
    return new DUPX("dup");
  }

  public static DUPX DUP2(ProgramCounter pc, MethodInfo method) {
    return new DUPX("dup2");
  }
  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    int origin_top = opStack.top();
    int capacity = (opStack.slots().types()[origin_top] == Long.class || opStack.slots().types()[origin_top] == Double.class) ? 2 : 1;
    if (name.equals("dup") && capacity == 1) {
      opStack.slots().copyTo(origin_top, 1, opStack.slots(), origin_top + 1);
    }
    else if (name.equals("dup2")){
      opStack.slots().copyTo(origin_top, 1, opStack.slots(), origin_top + 1);
      if (opStack.top() - origin_top == 1) {
        opStack.slots().copyTo(origin_top, 1, opStack.slots(), origin_top + 1);
      }
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
