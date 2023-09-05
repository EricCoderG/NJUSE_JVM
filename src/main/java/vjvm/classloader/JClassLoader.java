package vjvm.classloader;

import lombok.var;
import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.classloader.searchpath.ClassSearchPath;
import vjvm.runtime.JClass;
import vjvm.vm.VMContext;
import vjvm.utils.UnimplementedError;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.HashMap;

public class JClassLoader implements Closeable {
  private final JClassLoader parent;
  private final ClassSearchPath[] searchPaths;
  private final HashMap<String, JClass> definedClass = new HashMap<>();
  @Getter
  private final VMContext context;


  public JClassLoader(JClassLoader parent, ClassSearchPath[] searchPaths, VMContext context) {
    this.context = context;
    this.parent = parent;
    this.searchPaths = searchPaths;
  }

  /**
   * Load class
   *
   * If a class is found, construct it using the data returned by ClassSearchPath.findClass and return it.
   *
   * Otherwise, return null.
   */
  public JClass loadClass(String descriptor) {

    String className = descriptor.replaceFirst("L", "").replace(";", "");
    JClass jClass = null;

    //if the class which needs to load is in the definedClass
    if (definedClass.containsKey(className)) {
      return definedClass.get(className);
    }
    if (parent != null) {
      jClass = parent.loadClass(descriptor);
      if (jClass != null) {
        return jClass;
      }
    }

    //if parent do not find the class which needs to load
    InputStream dataInput = null;
    for (ClassSearchPath searchPath : searchPaths) {
      dataInput = searchPath.findClass(className);
      if (dataInput != null) {
        jClass = new JClass(new DataInputStream(dataInput), this);
        definedClass.put(className, jClass);
        return jClass;
      }
    }

    return null;
    //throw new UnimplementedError("TODO: load class");

    // To construct a JClass, use the following constructor
    // return new JClass(new DataInputStream(istream_from_file), this);
  }

  @Override
  @SneakyThrows
  public void close() {
    for (var s : searchPaths)
      s.close();
  }
}
