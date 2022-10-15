package vjvm.interpreter.instruction.control.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XOPR extends Instruction {
  private final String name;
  public static XOPR IADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR("iadd");
  }
  public static XOPR LADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR("ladd");
  }

  public static XOPR FADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR("fadd");
  }

  public static XOPR DADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR("dadd");
  }

  public static XOPR ISUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR("isub");
  }

  public static XOPR LSUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR("lsub");
  }

  public static XOPR FSUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR("fsub");
  }

  public static XOPR DSUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR("dsub");
  }

  public static XOPR IMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR("imul");
  }

  public static XOPR LMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR("lmul");
  }

  public static XOPR FMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR("fmul");
  }

  public static XOPR DMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR("dmul");
  }

  public static XOPR IDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR("idiv");
  }

  public static XOPR LDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR("ldiv");
  }

  public static XOPR FDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR("fdiv");
  }

  public static XOPR DDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR("ddiv");
  }

  public static XOPR IREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR("irem");
  }

  public static XOPR LREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR("lrem");
  }

  public static XOPR FREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR("frem");
  }

  public static XOPR DREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR("drem");
  }

  public static XOPR IAND(ProgramCounter pc, MethodInfo method) {
    return new XOPR("iand");
  }

  public static XOPR LAND(ProgramCounter pc, MethodInfo method) {
    return new XOPR("land");
  }

  public static XOPR IOR(ProgramCounter pc, MethodInfo method) {
    return new XOPR("ior");
  }

  public static XOPR LOR(ProgramCounter pc, MethodInfo method) {
    return new XOPR("lor");
  }

  public static XOPR IXOR(ProgramCounter pc, MethodInfo method) {
    return new XOPR("ixor");
  }

  public static XOPR LXOR(ProgramCounter pc, MethodInfo method) {
    return new XOPR("lxor");
  }

  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    if (name.startsWith("i")) {
      int value2 = opStack.popInt();
      int value1 = opStack.popInt();
      if (name.endsWith("add")) {
        opStack.pushInt(value1 + value2);
      }
      else if (name.endsWith("sub")) {
        opStack.pushInt(value1 - value2);
      }
      else if (name.endsWith("mul")) {
        opStack.pushInt(value1 * value2);
      }
      else if (name.endsWith("div")) {
        opStack.pushInt(value1 / value2);
      }
      else if (name.endsWith("rem")) {
        opStack.pushInt(value1 % value2);
      }
      else if (name.endsWith("and")) {
        opStack.pushInt(value1 & value2);
      }
      else if (name.endsWith("or") && !name.contains("x")) {
        opStack.pushInt(value1 | value2);
      }
      else if (name.endsWith("xor")) {
        opStack.pushInt(value1 ^ value2);
      }
    }
    if (name.startsWith("l")) {
      long value2 = opStack.popLong();
      long value1 = opStack.popLong();
      if (name.endsWith("add")) {
        opStack.pushLong(value1 + value2);
      }
      else if (name.endsWith("sub")) {
        opStack.pushLong(value1 - value2);
      }
      else if (name.endsWith("mul")) {
        opStack.pushLong(value1 * value2);
      }
      else if (name.endsWith("div")) {
        opStack.pushLong(value1 / value2);
      }
      else if (name.endsWith("rem")) {
        opStack.pushLong(value1 % value2);
      }
      else if (name.endsWith("and")) {
        opStack.pushLong(value1 & value2);
      }
      else if (name.endsWith("or") && !name.contains("x")) {
        opStack.pushLong(value1 | value2);
      }
      else if (name.endsWith("xor")) {
        opStack.pushLong(value1 ^ value2);
      }
    }
    if (name.startsWith("f")) {
      float value2 = opStack.popFloat();
      float value1 = opStack.popFloat();
      if (name.endsWith("add")) {
        opStack.pushFloat(value1 + value2);
      }
      else if (name.endsWith("sub")) {
        opStack.pushFloat(value1 - value2);
      }
      else if (name.endsWith("mul")) {
        opStack.pushFloat(value1 * value2);
      }
      else if (name.endsWith("div")) {
        opStack.pushFloat(value1 / value2);
      }
      else if (name.endsWith("rem")) {
        opStack.pushFloat(value1 % value2);
      }
    }
    if (name.startsWith("d")) {
      double value2 = opStack.popDouble();
      double value1 = opStack.popDouble();
      if (name.endsWith("add")) {
        opStack.pushDouble(value1 + value2);
      }
      else if (name.endsWith("sub")) {
        opStack.pushDouble(value1 - value2);
      }
      else if (name.endsWith("mul")) {
        opStack.pushDouble(value1 * value2);
      }
      else if (name.endsWith("div")) {
        opStack.pushDouble(value1 / value2);
      }
      else if (name.endsWith("rem")) {
        opStack.pushDouble(value1 % value2);
      }
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
