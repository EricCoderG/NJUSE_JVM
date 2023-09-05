package vjvm.interpreter.instruction.loads;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XLOAD extends Instruction {

  private final int index;

  private final String name;

  public static XLOAD ILOAD(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(pc.ubyte(),  "iload");
  }

  public static XLOAD ILOAD_0(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(0,  "iload_0");
  }

  public static XLOAD ILOAD_1(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(1,  "iload_1");
  }

  public static XLOAD ILOAD_2(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(2,  "iload_2");
  }

  public static XLOAD ILOAD_3(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(3,  "iload_3");
  }

  public static XLOAD LLOAD(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(pc.ubyte(),  "lload");
  }

  public static XLOAD LLOAD_0(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(0,  "lload_0");
  }

  public static XLOAD LLOAD_1(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(1,  "lload_1");
  }

  public static XLOAD LLOAD_2(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(2,  "lload_2");
  }

  public static XLOAD LLOAD_3(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(3,  "lload_3");
  }

  public static XLOAD FLOAD(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(pc.ubyte(),  "fload");
  }

  public static XLOAD FLOAD_0(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(0,  "fload_0");
  }

  public static XLOAD FLOAD_1(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(1,  "fload_1");
  }

  public static XLOAD FLOAD_2(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(2,  "fload_2");
  }

  public static XLOAD FLOAD_3(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(3,  "fload_3");
  }

  public static XLOAD DLOAD(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(pc.ubyte(),  "dload");
  }

  public static XLOAD DLOAD_0(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(0,  "dload_0");
  }

  public static XLOAD DLOAD_1(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(1,  "dload_1");
  }

  public static XLOAD DLOAD_2(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(2,  "dload_2");
  }

  public static XLOAD DLOAD_3(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(3,  "dload_3");
  }

  @Override
  public void run(JThread thread) {
    var slots = thread.top().vars();
    var stack = thread.top().stack();
    if (name.startsWith("iload")) {
      stack.pushInt(slots.int_(index));
    }
    if (name.startsWith("lload")) {
      stack.pushLong(slots.long_(index));
    }
    if (name.startsWith("fload")) {
      stack.pushFloat(slots.float_(index));
    }
    if (name.startsWith("dload")) {
      stack.pushDouble(slots.double_(index));
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
