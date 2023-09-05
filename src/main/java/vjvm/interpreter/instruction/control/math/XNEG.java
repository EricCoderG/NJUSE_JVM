package vjvm.interpreter.instruction.control.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XNEG extends Instruction {

  private final String name;

  public static XNEG INEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG("ineg");
  }
  public static XNEG LNEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG("lneg");
  }
  public static XNEG FNEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG("fneg");
  }
  public static XNEG DNEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG("dneg");
  }

  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    if (name.startsWith("i")) {
      int value = opStack.popInt();
      opStack.pushInt(-value);
    }
    if (name.startsWith("l")) {
      long value = opStack.popLong();
      opStack.pushLong(-value);
    }
    if (name.startsWith("f")) {
      float value = opStack.popFloat();
      opStack.pushFloat(-value);
    }
    if (name.startsWith("d")) {
      double value = opStack.popDouble();
      opStack.pushDouble(-value);
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
