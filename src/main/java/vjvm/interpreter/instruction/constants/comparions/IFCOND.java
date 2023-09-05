package vjvm.interpreter.instruction.constants.comparions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IFCOND extends Instruction {

  private final int offset;
  private final String name;

  public static IFCOND IFEQ(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(pc.short_() - 3, "ifeq");
  }
  public static IFCOND IFNE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(pc.short_() - 3, "ifne");
  }
  public static IFCOND IFLT(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(pc.short_() - 3, "iflt");
  }
  public static IFCOND IFGE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(pc.short_() - 3, "ifge");
  }
  public static IFCOND IFGT(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(pc.short_() - 3, "ifgt");
  }
  public static IFCOND IFLE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(pc.short_() - 3, "ifle");
  }
  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    var pc = thread.pc();
    int value = opStack.popInt();
    switch (name.substring(2,4)) {
      case "eq":
        if (value == 0) {
          pc.move(offset);
        }
        break;
      case "ne":
        if (value != 0) {
          pc.move(offset);
        }
        break;
      case "lt":
        if (value < 0) {
          pc.move(offset);
        }
        break;
      case "ge":
        if (value >= 0) {
          pc.move(offset);
        }
        break;
      case "gt":
        if (value > 0) {
          pc.move(offset);
        }
        break;
      case "le":
        if (value <= 0) {
          pc.move(offset);
        }
        break;
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
