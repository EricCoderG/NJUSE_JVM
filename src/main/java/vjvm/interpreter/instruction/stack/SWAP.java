package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SWAP extends Instruction {

  private final String name;

  public static SWAP SWAP(ProgramCounter pc, MethodInfo method) {
    return new SWAP("swap");
  }

  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    int origin_top = opStack.top();
    int capacity1 = (opStack.slots().types()[origin_top] == Long.class || opStack.slots().types()[origin_top] == Double.class) ? 2 : 1;
    int capacity2 = (opStack.slots().types()[origin_top - 1] == Long.class || opStack.slots().types()[origin_top - 1] == Double.class) ? 2 : 1;
    if (capacity1 == 1 && capacity2 == 1) {

      int temp1 = opStack.popInt();
      int temp2 = opStack.popInt();
      opStack.pushInt(temp1);
      opStack.pushInt(temp2);

      var tmp = opStack.slots().types()[origin_top];
      opStack.slots().types()[origin_top] = opStack.slots().types()[origin_top - 1];
      opStack.slots().types()[origin_top - 1] = tmp;

    }
  }

  @Override
  public String toString() {
    return name;
  }
}
