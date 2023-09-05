package vjvm.vm;

import java.util.ArrayList;

import lombok.Getter;
import lombok.var;
import vjvm.classloader.JClassLoader;
import vjvm.classloader.searchpath.ClassSearchPath;
import vjvm.classloader.searchpath.ModuleSearchPath;
import vjvm.interpreter.JInterpreter;
import vjvm.interpreter.JMonitor;
import vjvm.runtime.JThread;
import vjvm.runtime.Slots;

public class VMContext {
  @Getter
  private final JClassLoader bootstrapLoader;
  @Getter
  private final JClassLoader userLoader;
  @Getter
  private final JInterpreter interpreter;
  @Getter
  private final JMonitor monitor;
  private final ArrayList<JThread> threads = new ArrayList<>();

  public VMContext(String userClassPath) {
    interpreter = new JInterpreter();
    monitor = new JMonitor(this);

    bootstrapLoader = new JClassLoader(
      null,
      getSystemSearchPaths(),
      this
    );

    userLoader = new JClassLoader(
      bootstrapLoader,
      ClassSearchPath.constructSearchPath(userClassPath),
      this
    );
  }

  void run(String entryClass) {
    var initThread = new JThread(this);
    threads.add(initThread);

    var entry = userLoader.loadClass('L' + entryClass.replace('.', '/') + ';');

    var mainMethod = entry.findMethod("main", "([Ljava/lang/String;)V");
    assert mainMethod.jClass() == entry;
    interpreter.invoke(mainMethod, initThread, new Slots(1));
  }

  private static ClassSearchPath[] getSystemSearchPaths() {
    var bootClassPath = System.getProperty("sun.boot.class.path");

    if (bootClassPath != null) {
      return ClassSearchPath.constructSearchPath(bootClassPath);
    }

    // For compatibility with JDK9+
    return new ClassSearchPath[] { new ModuleSearchPath() };
  }
}
