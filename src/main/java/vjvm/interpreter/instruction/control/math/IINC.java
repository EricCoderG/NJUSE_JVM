package vjvm.interpreter.instruction.control.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IINC extends Instruction {

  private final int index;
  private final int Const;
  private final String name;

  public static IINC IINC(ProgramCounter pc, MethodInfo method) {
    return new IINC(pc.ubyte(), pc.byte_(), "iinc");
  }


  @Override
  public void run(JThread thread) {
    var slots = thread.top().vars();
    int local_variable = slots.int_(index);
    local_variable += Const;
    slots.int_(index, local_variable);
  }

  @Override
  public String toString() {
    return name;
  }
}
