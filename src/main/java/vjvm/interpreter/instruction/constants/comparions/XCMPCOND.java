package vjvm.interpreter.instruction.constants.comparions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XCMPCOND extends Instruction {

  private final String name;

  public static XCMPCOND FCMPL(ProgramCounter pc, MethodInfo method) {
    return new XCMPCOND("fcmpl");
  }

  public static XCMPCOND FCMPG(ProgramCounter pc, MethodInfo method) {
    return new XCMPCOND("fcmpg");
  }

  public static XCMPCOND DCMPL(ProgramCounter pc, MethodInfo method) {
    return new XCMPCOND("dcmpl");
  }

  public static XCMPCOND DCMPG(ProgramCounter pc, MethodInfo method) {
    return new XCMPCOND("dcmpg");
  }
  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    if (name.startsWith("f")) {
      float value2 = opStack.popFloat();
      float value1 = opStack.popFloat();
      if (Float.isNaN(value1) || Float.isNaN(value2)) {
        if (name.endsWith("g")) {
          opStack.pushInt(1);
        } else {
          opStack.pushInt(-1);
        }
        return;
      }
      if (value1 > value2) {
        opStack.pushInt(1);
      }
      else if (value1 == value2) {
        opStack.pushInt(0);
      } else {
        opStack.pushInt(-1);
      }
    }
    if (name.startsWith("d")) {
      double value2 = opStack.popDouble();
      double value1 = opStack.popDouble();
      if (Double.isNaN(value1) || Double.isNaN(value2)) {
        if (name.endsWith("g")) {
          opStack.pushInt(1);
        } else {
          opStack.pushInt(-1);
        }
        return;
      }
      if (value1 > value2) {
        opStack.pushInt(1);
      }
      else if (value1 == value2) {
        opStack.pushInt(0);
      } else {
        opStack.pushInt(-1);
      }
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
