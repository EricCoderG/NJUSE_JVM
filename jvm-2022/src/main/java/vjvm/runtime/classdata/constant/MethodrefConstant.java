package vjvm.runtime.classdata.constant;


import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class MethodrefConstant extends Constant{

  @Getter
  private final int classIndex;
  private final int name_and_typeIndex;
  private final JClass self;
  private String classname;
  private String name;
  private String descriptor;

  @SneakyThrows
  public MethodrefConstant(DataInput dataInput, JClass self) {
    classIndex = dataInput.readUnsignedShort();
    name_and_typeIndex = dataInput.readUnsignedShort();
    this.self = self;
  }

  public String className() {
    if (classname == null) {
      classname = ((ClassConstant) self.constantPool().constant(classIndex)).name();
    }
    return classname;
  }

  public String name() {
    if (name == null) {
      name = ((NameAndTypeConstant) self.constantPool().constant(name_and_typeIndex)).name();
    }
    return name;
  }

  public String descriptor() {
    if (descriptor == null) {
      descriptor = ((NameAndTypeConstant) self.constantPool().constant(name_and_typeIndex)).type();
    }
    return descriptor;
  }

  @Override
  public String toString() {
    return String.format("Methodref: %s.%s%s", className(), name(), descriptor());
  }


}
