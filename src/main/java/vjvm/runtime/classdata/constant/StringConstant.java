package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class StringConstant extends Constant{

  @Getter
  private final int string_index;
  private final JClass self;
  private String name;

  @SneakyThrows
  StringConstant(DataInput input, JClass self) {
    string_index = input.readUnsignedShort();
    this.self = self;
  }

  public String name() {
    if (name == null) {
      name = ((UTF8Constant) self.constantPool().constant(string_index)).value();
    }
    return name;
  }

  @Override
  public String toString() {
    return String.format("String: \"%s\"", name());
  }
}
