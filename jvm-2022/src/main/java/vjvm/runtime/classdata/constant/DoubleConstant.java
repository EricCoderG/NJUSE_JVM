package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.var;
import lombok.SneakyThrows;

import java.io.DataInput;

import static java.lang.Double.NaN;

public class DoubleConstant extends Constant {
  @Getter
  private double ret;

  @SneakyThrows
  DoubleConstant(DataInput input) {
    ret = input.readDouble();
  }

  @Override
  public String toString() {
    return String.format("Double: %a", ret);
  }
}
