package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class ClassConstant extends Constant {

  @Getter
  private final int nameIndex;
  private final JClass self;
  private String name;

  @SneakyThrows
  ClassConstant(DataInput input, JClass self) {
    super();
    nameIndex = input.readUnsignedShort();
    this.self = self;
  }

  public String name() {
    if (name == null) {
      name = ((UTF8Constant) self.constantPool().constant(nameIndex)).value();
    }
    return name;
  }

  @Override
  public String toString() {
    return String.format("Class: %s", name());
  }
}
