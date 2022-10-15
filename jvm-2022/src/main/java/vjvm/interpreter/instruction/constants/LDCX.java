package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.*;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LDCX extends Instruction {

  private final Constant constant;
  private final String name;

  public static LDCX LDC(ProgramCounter pc, MethodInfo method) {
    int index = pc.ubyte();
    return new LDCX(method.jClass().constantPool().constant(index), "ldc");
  }

  public static LDCX LDCW(ProgramCounter pc, MethodInfo method) {
    int index = pc.ushort();
    return new LDCX(method.jClass().constantPool().constant(index), "ldc_w");
  }

  public static LDCX LDC2W(ProgramCounter pc, MethodInfo method) {
    int index = pc.ushort();
    return new LDCX(method.jClass().constantPool().constant(index), "ldc2_w");
  }

  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    if (constant.toString().startsWith("Integer")) {
      opStack.pushInt(((IntegerConstant) constant).value());
    }
    if (constant.toString().startsWith("Float")) {
      opStack.pushFloat(((FloatConstant)constant).ret());
    }
    if (constant.toString().startsWith("Long") && name.equals("ldc2_w")) {
      opStack.pushLong(((LongConstant)constant).value());
    }
    if (constant.toString().startsWith("Double") && name.equals("ldc2_w")) {
      opStack.pushDouble(((DoubleConstant)constant).ret());
    }
  }

  @Override
  public String toString() {
    return name + constant.toString();
  }
}
