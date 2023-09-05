package vjvm.interpreter.instruction.control.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LIOPR extends Instruction {

  private final String name;

  public static LIOPR ISHL(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("ishl");
  }

  public static LIOPR LSHL(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("lshl");
  }

  public static LIOPR ISHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("ishr");
  }

  public static LIOPR LSHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("lshr");
  }

  public static LIOPR IUSHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("iushr");
  }

  public static LIOPR LUSHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("lushr");
  }

  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    if (name.startsWith("i")) {
      int value2 = opStack.popInt();
      int value1 = opStack.popInt();
      int s = value2 % 32;
      if (name.endsWith("shl")) {
        opStack.pushInt(value1 << s);
      }
      if (name.endsWith("shr") && !name.contains("u")) {
        opStack.pushInt(value1 >> s);
      }
      if (name.endsWith("ushr")) {
        if (value1 >= 0) {
          opStack.pushInt(value1 >> s);
        } else {
          opStack.pushInt(value1 >>> s);
        }
      }
    }
    if (name.startsWith("l")) {
      int value2 = opStack.popInt();
      long value1 = opStack.popLong();
      int s = value2 % 64;
      if (name.endsWith("shl")) {
        opStack.pushLong(value1 << s);
      }
      else if (name.endsWith("shr") && !name.contains("u")) {
        opStack.pushLong(value1 >> s);
      }
      else if (name.endsWith("ushr")) {
        if (value1 >= 0) {
          opStack.pushLong(value1 >> s);
        } else {
          opStack.pushLong(value1 >>> s);
        }
      }
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
