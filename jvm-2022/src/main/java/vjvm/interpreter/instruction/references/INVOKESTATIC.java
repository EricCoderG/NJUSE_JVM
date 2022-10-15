package vjvm.interpreter.instruction.references;

import lombok.SneakyThrows;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JClass;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.MethodrefConstant;

public class INVOKESTATIC extends Instruction {
  private final MethodInfo method;

  @SneakyThrows
  public INVOKESTATIC(ProgramCounter pc, MethodInfo method) {
    // TODO: decode invokestatic
    int index = pc.ushort();
    MethodrefConstant Methodref_info = (MethodrefConstant) method.jClass().constantPool().constant(index);
    JClass thisClass = method.jClass();
    JClass jClass = thisClass.classLoader().loadClass(Methodref_info.className());
    this.method = jClass.findMethod(Methodref_info.name(), Methodref_info.descriptor());
    //throw new UnimplementedError();
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    var args = stack.popSlots(method.argc());
    thread.context().interpreter().invoke(method, thread, args);
  }

  @Override
  public String toString() {
    return String.format("invokestatic %s:%s:%s", method.jClass().name(), method.name(), method.descriptor());
  }
}
