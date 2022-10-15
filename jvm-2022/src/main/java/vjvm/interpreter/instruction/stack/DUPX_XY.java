package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DUPX_XY extends Instruction {

  public static DUPX_XY DUP_X1(ProgramCounter pc, MethodInfo method) {
    return new DUPX_XY("dup_x1");
  }

  public static DUPX_XY DUP_X2(ProgramCounter pc, MethodInfo method) {
    return new DUPX_XY("dup_x2");
  }

  public static DUPX_XY DUP2_X1(ProgramCounter pc, MethodInfo method) {
    return new DUPX_XY("dup2_x1");
  }

  public static DUPX_XY DUP2_X2(ProgramCounter pc, MethodInfo method) {
    return new DUPX_XY("dup2_x2");
  }


  private final String name;
  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    int origin_top = opStack.top();
    int capacity1 = (opStack.slots().types()[origin_top] == Long.class || opStack.slots().types()[origin_top] == Double.class) ? 2 : 1;
    int capacity2 = (opStack.slots().types()[origin_top - 1] == Long.class || opStack.slots().types()[origin_top - 1] == Double.class) ? 2 : 1;
    if (name.equals("dup_x1") && capacity1 == 1 && capacity2 == 1) {
      opStack.slots().copyTo(origin_top - 1, 2, opStack.slots(), origin_top + 1);
      return;
    }
    if (name.equals("dup2_x2") && capacity1 == 2 && capacity2 == 2) {
      opStack.slots().copyTo(origin_top - 3, 4, opStack.slots(), origin_top + 1);
      return;
    }
    if ((name.equals("dup2_x1") && capacity1 == 2 && capacity2 == 1) ||
      (name.equals("dup_x2") && capacity1 == 1 && capacity2 == 2)) {
      opStack.slots().copyTo(origin_top - 2, 3, opStack.slots(), origin_top + 1);
      return;
    }
    int capacity3 = (opStack.slots().types()[origin_top - 2] == Long.class || opStack.slots().types()[origin_top - 2] == Double.class) ? 2 : 1;
    if ((name.equals("dup_x2") || name.equals("dup2_x1")) && capacity1 == 1 && capacity2 == 1 && capacity3 == 1) {
      opStack.slots().copyTo(origin_top - 2, 3, opStack.slots(), origin_top + 1);
      return;
    }
    if (name.equals("dup2_x2") && capacity2 == 1 && capacity1 + capacity3 == 3) {
      opStack.slots().copyTo(origin_top - 3, 4, opStack.slots(), origin_top + 1);
      return;
    }
    int capacity4 = (opStack.slots().types()[origin_top - 3] == Long.class || opStack.slots().types()[origin_top - 3] == Double.class) ? 2 : 1;
    if (name.equals("dup2_x2") && capacity1 == 1 && capacity2 == 1 && capacity3 == 1 && capacity4 == 1) {
      opStack.slots().copyTo(origin_top - 3, 4, opStack.slots(), origin_top + 1);
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
