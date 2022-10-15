package vjvm.runtime.classdata.constant;

import lombok.var;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

import static java.lang.Float.NaN;

public class FloatConstant extends Constant {
  @Getter
  private final float ret;

  @SneakyThrows
  FloatConstant(DataInput input) {
    ret =  input.readFloat();
  }

  @Override
  public String toString() {
    return String.format("Float: %a", ret);
  }
}
