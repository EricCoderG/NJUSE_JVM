package vjvm.interpreter.instruction.stores;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.interpreter.instruction.loads.XLOAD;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XSTORE extends Instruction {

  private final int index;

  private final String name;

  public static XSTORE ISTORE(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(pc.ubyte(),  "istore");
  }

  public static XSTORE ISTORE_0(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(0,  "istore_1");
  }

  public static XSTORE ISTORE_1(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(1,  "istore_1");
  }

  public static XSTORE ISTORE_2(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(2,  "istore_2");
  }

  public static XSTORE ISTORE_3(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(3,  "istore_3");
  }

  public static XSTORE LSTORE(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(pc.ubyte(),  "lstore");
  }

  public static XSTORE LSTORE_0(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(0,  "lstore_0");
  }

  public static XSTORE LSTORE_1(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(1,  "lstore_1");
  }

  public static XSTORE LSTORE_2(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(2,  "lstore_2");
  }

  public static XSTORE LSTORE_3(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(3,  "lstore_3");
  }

  public static XSTORE FSTORE(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(pc.ubyte(),  "fstore");
  }

  public static XSTORE FSTORE_0(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(0,  "fstore_0");
  }

  public static XSTORE FSTORE_1(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(1,  "fstore_1");
  }

  public static XSTORE FSTORE_2(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(2,  "fstore_2");
  }

  public static XSTORE FSTORE_3(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(3,  "fstore_3");
  }

  public static XSTORE DSTORE(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(pc.ubyte(),  "dstore");
  }

  public static XSTORE DSTORE_0(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(0,  "dstore_0");
  }

  public static XSTORE DSTORE_1(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(1,  "dstore_1");
  }

  public static XSTORE DSTORE_2(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(2,  "dstore_2");
  }

  public static XSTORE DSTORE_3(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(3,  "dstore_3");
  }

  @Override
  public void run(JThread thread) {
    var slots = thread.top().vars();
    var stack = thread.top().stack();
    if (name.startsWith("istore")) {
      int value = stack.popInt();
      slots.int_(index, value);
    }
    if (name.startsWith("lstore")) {
      long value = stack.popLong();
      slots.long_(index, value);
    }
    if (name.startsWith("fstore")) {
      float value = stack.popFloat();
      slots.float_(index, value);
    }
    if (name.startsWith("dstore")) {
      double value = stack.popDouble();
      slots.double_(index, value);
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
