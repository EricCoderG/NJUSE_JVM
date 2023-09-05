package vjvm.interpreter;

import lombok.Getter;
import lombok.var;

import org.apache.commons.lang3.tuple.Triple;
import vjvm.classfiledefs.MethodDescriptors;
import vjvm.interpreter.instruction.Decoder;
import vjvm.runtime.JFrame;
import vjvm.runtime.JThread;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.utils.InputUtils;

import java.util.*;
import java.util.function.BiFunction;

import static vjvm.classfiledefs.Descriptors.*;

public class JInterpreter {
  // (ClassName, MethodName, MethodDescriptor) -> HackFunction
  private static final HashMap<Triple<String, String, String>, BiFunction<JThread, Slots, Object>> nativeTable = new HashMap<>();

  @Getter
  private Status status = Status.CONTINUE;
  private long steps;

  private final ArrayList<Breakpoint> breakpoints = new ArrayList<>();

  /**
   * Invoke a method when there is no frames in a thread.
   *
   * @param method the method to call
   * @param thread the thread to run
   * @param args   the supplied arguments, index begins at 0
   */
  public void invoke(MethodInfo method, JThread thread, Slots args) {
    var frame = new JFrame(method, args);
    thread.push(frame);

    if (method.native_()) {
      runNativeMethod(thread);
    } else {
      run(thread);
    }
  }

  public void step(long steps) {
    assert steps >= 0;

    status = Status.STEP;
    this.steps = steps;
  }

  public void continue_() {
    status = Status.CONTINUE;
  }

  public void break_() {
    status = Status.BREAK;
  }

  public void setBreakpoint(MethodInfo method, int offset) {
    // TODO(optional): add and enable a breakpoint
  }

  public void removeBreakpoint(int index) {
    // TODO(optional): disable and remove the breakpoint at breakpoints[index]
  }

  public List<Breakpoint> breakpoints() {
    return Collections.unmodifiableList(breakpoints);
  }

  private void run(JThread thread) {
    var frame = thread.top();
    var monitor = thread.context().monitor();

    while (thread.top() == frame) {
      if (status == Status.STEP && steps == 0) {
        monitor.enter(thread);
      }

      var op = Decoder.decode(thread.pc(), frame.method());
      steps--;
      op.run(thread);

      // TODO(optional): handle breakpoints
    }
  }

  private void runNativeMethod(JThread thread) {
    var frame = thread.top();
    var method = frame.method();
    assert method.native_();

    var key = Triple.of(method.jClass().name(), method.name(), method.descriptor());
    var impl = nativeTable.get(key);
    if (impl == null) {
      throw new Error(String.format("Unimplemented native method: %s", key));
    }

    var ret = impl.apply(thread, frame.vars());

    thread.pop();
    var s = thread.top().stack();

    switch (MethodDescriptors.returnType(method.descriptor())) {
    case DESC_void:
      break;
    case DESC_boolean:
      s.pushInt((((Boolean) ret) ? 1 : 0));
      break;
    case DESC_byte:
      s.pushInt((Byte) ret);
      break;
    case DESC_char:
      s.pushInt((Character) ret);
      break;
    case DESC_double:
      s.pushDouble((Double) ret);
      break;
    case DESC_float:
      s.pushFloat((Float) ret);
      break;
    case DESC_int:
      s.pushInt((Integer) ret);
      break;
    case DESC_long:
      s.pushLong((Long) ret);
      break;
    case DESC_short:
      s.pushInt((Short) ret);
      break;
    default:
      throw new Error("Invalid return type");
    }
  }

  public static enum Status {
    CONTINUE, STEP, BREAK,
  }

  static {
    nativeTable.put(Triple.of("lab2/IOUtil", "readInt", "()I"), (t, a) -> InputUtils.readInt());
    nativeTable.put(Triple.of("lab2/IOUtil", "readLong", "()J"), (t, a) -> InputUtils.readLong());
    nativeTable.put(Triple.of("lab2/IOUtil", "readChar", "()C"), (t, a) -> InputUtils.readChar());
    nativeTable.put(Triple.of("lab2/IOUtil", "writeInt", "(I)V"), (t, a) -> {
      System.out.println(a.int_(0));
      return null;
    });
    nativeTable.put(Triple.of("lab2/IOUtil", "writeFloat", "(F)V"), (t, a) -> {
      System.out.println(a.float_(0));
      return null;
    });
    nativeTable.put(Triple.of("lab2/IOUtil", "writeLong", "(J)V"), (t, a) -> {
      System.out.println(a.long_(0));
      return null;
    });
    nativeTable.put(Triple.of("lab2/IOUtil", "writeDouble", "(D)V"), (t, a) -> {
      System.out.println(a.double_(0));
      return null;
    });
    nativeTable.put(Triple.of("lab2/IOUtil", "writeChar", "(C)V"), (t, a) -> {
      System.out.println(a.char_(0));
      return null;
    });
  }
}
