package vjvm.interpreter.instruction.constants.comparions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IF_XCMPCOND extends Instruction {

  private final int offset;

  private final String name;

  public static IF_XCMPCOND IF_ICMPEQ (ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND(pc.short_() - 3, "if_icmpeq");
  }

  public static IF_XCMPCOND IF_ICMPNE (ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND(pc.short_() - 3, "if_icmpne");
  }

  public static IF_XCMPCOND IF_ICMPLT (ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND(pc.short_() - 3, "if_icmplt");
  }

  public static IF_XCMPCOND IF_ICMPGE (ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND(pc.short_() - 3, "if_icmpge");
  }

  public static IF_XCMPCOND IF_ICMPGT (ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND(pc.short_() - 3, "if_icmpgt");
  }

  public static IF_XCMPCOND IF_ICMPLE (ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND(pc.short_() - 3, "if_icmple");
  }


  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    var pc = thread.pc();
    int value2 = opStack.popInt();
    int value1 = opStack.popInt();
    switch (name.substring(7,9)) {
      case "eq":
        if (value1 == value2) {
          pc.move(offset);
        }
        break;
      case "ne":
        if (value1 != value2) {
          pc.move(offset);
        }
        break;
      case "lt":
        if (value1 < value2) {
          pc.move(offset);
        }
        break;
      case "ge":
        if (value1 >= value2) {
          pc.move(offset);
        }
        break;
      case "gt":
        if (value1 > value2) {
          pc.move(offset);
        }
        break;
      case "le":
        if (value1 <= value2) {
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
