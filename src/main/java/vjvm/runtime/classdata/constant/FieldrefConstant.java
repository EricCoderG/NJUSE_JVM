package vjvm.runtime.classdata.constant;


import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class FieldrefConstant extends Constant{

  private final int classIndex;
  private final int name_and_typeIndex;
  private final JClass self;
  private String name;
  private String name_and_type;


  @SneakyThrows
  public FieldrefConstant(DataInput dataInput, JClass self) {
    classIndex = dataInput.readUnsignedShort();
    name_and_typeIndex = dataInput.readUnsignedShort();
    this.self = self;
  }

  String className() {
    if (name == null) {
      name = ((ClassConstant) self.constantPool().constant(classIndex)).name();
    }
    return name;
  }

  String name_and_type() {
    if (name_and_type == null) {
      name_and_type = ((NameAndTypeConstant) self.constantPool().constant(name_and_typeIndex)).name() + ":" + ((NameAndTypeConstant) self.constantPool().constant(name_and_typeIndex)).type();
    }
    return name_and_type;
  }

  @Override
  public String toString() {
    return String.format("Fieldref: %s.%s", className(), name_and_type());
  }
}
