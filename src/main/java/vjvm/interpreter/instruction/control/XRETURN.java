package vjvm.interpreter.instruction.control;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XRETURN<T> extends Instruction {
  private final Function<OperandStack, T> popFunc;
  private final Function<T, T> convertFunc;
  private final BiConsumer<OperandStack, T> pushFunc;
  private final String name;

  public static XRETURN<Void> RETURN(ProgramCounter pc, MethodInfo method) {
    return new XRETURN<>(s -> null, Function.identity(), null, "return");
  }

  public static XRETURN<Integer> IRETURN(ProgramCounter pc, MethodInfo method) {
    return new XRETURN<>(OperandStack::popInt, Function.identity(), OperandStack::pushInt, "ireturn");
  }

  public static XRETURN<Long> LRETURN(ProgramCounter pc, MethodInfo method) {
    return new XRETURN<>(OperandStack::popLong, Function.identity(), OperandStack::pushLong, "lreturn");
  }

  public static XRETURN<Float> FRETURN(ProgramCounter pc, MethodInfo method) {
    return new XRETURN<>(OperandStack::popFloat, Function.identity(), OperandStack::pushFloat, "freturn");
  }

  public static XRETURN<Double> DRETURN(ProgramCounter pc, MethodInfo method) {
    return new XRETURN<>(OperandStack::popDouble, Function.identity(), OperandStack::pushDouble, "dreturn");
  }

  @Override
  public void run(JThread thread) {
    var returnValue = popFunc.apply(thread.top().stack());
    thread.pop();

    if (pushFunc != null) {
      pushFunc.accept(thread.top().stack(), convertFunc.apply(returnValue));
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
