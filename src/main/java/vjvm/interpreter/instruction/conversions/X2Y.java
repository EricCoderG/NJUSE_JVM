package vjvm.interpreter.instruction.conversions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class X2Y extends Instruction {

  private final String name;

  public static X2Y I2L(ProgramCounter pc, MethodInfo method) {
    return new X2Y("i2l");
  }

  public static X2Y I2F(ProgramCounter pc, MethodInfo method) {
    return new X2Y("i2f");
  }

  public static X2Y I2D(ProgramCounter pc, MethodInfo method) {
    return new X2Y("i2d");
  }

  public static X2Y L2I(ProgramCounter pc, MethodInfo method) {
    return new X2Y("l2i");
  }

  public static X2Y L2F(ProgramCounter pc, MethodInfo method) {
    return new X2Y("l2f");
  }

  public static X2Y L2D(ProgramCounter pc, MethodInfo method) {
    return new X2Y("l2d");
  }

  public static X2Y F2I(ProgramCounter pc, MethodInfo method) {
    return new X2Y("f2i");
  }

  public static X2Y F2L(ProgramCounter pc, MethodInfo method) {
    return new X2Y("f2l");
  }

  public static X2Y F2D(ProgramCounter pc, MethodInfo method) {
    return new X2Y("f2d");
  }

  public static X2Y D2I(ProgramCounter pc, MethodInfo method) {
    return new X2Y("d2i");
  }

  public static X2Y D2L(ProgramCounter pc, MethodInfo method) {
    return new X2Y("d2l");
  }

  public static X2Y D2F(ProgramCounter pc, MethodInfo method) {
    return new X2Y("d2f");
  }

  public static X2Y I2B(ProgramCounter pc, MethodInfo method) {
    return new X2Y("i2b");
  }

  public static X2Y I2C(ProgramCounter pc, MethodInfo method) {
    return new X2Y("i2c");
  }

  public static X2Y I2S(ProgramCounter pc, MethodInfo method) {
    return new X2Y("i2s");
  }

  @Override
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    if (name.startsWith("i")) {
      int value = opStack.popInt();
      switch (name.charAt(2)) {
        case 'l':
          opStack.pushLong(Long.parseLong(value + ""));
          break;
        case 'f':
          opStack.pushFloat(Float.parseFloat(value + ""));
          break;
        case 'd':
          opStack.pushDouble(Double.parseDouble(value + ""));
          break;
        case 'b':
          opStack.pushByte((byte) value);
          break;
        case 'c':
          opStack.pushChar((char) value);
          break;
        case 's':
          opStack.pushShort((short) value);
          break;
      }
    }
    if (name.startsWith("l")) {
      long value = opStack.popLong();
      switch (name.charAt(2)) {
        case 'i':
          opStack.pushInt((int) value);
          break;
        case 'f':
          opStack.pushFloat(Float.parseFloat(value + ""));
          break;
        case 'd':
          opStack.pushDouble(Double.parseDouble(value + ""));
          break;
      }
    }
    if (name.startsWith("f")) {
      float value = opStack.popFloat();
      switch (name.charAt(2)) {
        case 'i':
          opStack.pushInt((int) value);
          break;
        case 'l':
          if (Float.isNaN(value)) {
            opStack.pushLong(0L);
            return;
          }
          if (value > Long.MAX_VALUE) {
            opStack.pushLong(Long.MAX_VALUE);
            return;
          }
          if (value < Long.MIN_VALUE) {
            opStack.pushLong(Long.MIN_VALUE);
            return;
          }
          opStack.pushLong((long)value);
          break;
        case 'd':
          opStack.pushDouble(Double.parseDouble(value + ""));
          break;
      }
    }
    if (name.startsWith("d")) {
      double value = opStack.popDouble();
      switch (name.charAt(2)) {
        case 'i':
          if (Double.isNaN(value)) {
            opStack.pushInt(0);
            return;
          }
          if (value > Integer.MAX_VALUE) {
            opStack.pushInt(Integer.MAX_VALUE);
            return;
          }
          if (value < Integer.MIN_VALUE) {
            opStack.pushInt(Integer.MIN_VALUE);
            return;
          }
          opStack.pushInt((int) (value));
          break;
        case 'l':
          opStack.pushLong((long) value);
          break;
        case 'f':
          if (Double.isNaN(value)) {
            opStack.pushFloat(Float.NaN);
            return;
          }
          opStack.pushFloat(Float.parseFloat(value + ""));
          break;
      }
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
