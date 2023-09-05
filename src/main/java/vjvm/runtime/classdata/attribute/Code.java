package vjvm.runtime.classdata.attribute;

import lombok.var;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.utils.UnimplementedError;

import java.io.DataInput;

@Getter
public class Code extends Attribute {
  private final int maxStack;
  private final int maxLocals;
  private final byte[] code; // the bytecode represented as raw bytes
  private final Attribute[] attributes;

  @SneakyThrows
  Code(DataInput input, ConstantPool constantPool) {
    // TODO: construct code
    this.maxStack = input.readUnsignedShort();
    this.maxLocals = input.readUnsignedShort();
    int code_length = input.readInt();
    code = new byte[code_length];
    for (int i = 0; i < code_length; i++) {
      code[i] = (byte) input.readUnsignedByte();
    }
    int exception_table_length = input.readUnsignedShort();
    //Undo :Exception_table
    for (int i = 0; i < exception_table_length; i++) {
      int start_pc = input.readUnsignedShort();
      int end_pc = input.readUnsignedShort();
      int handler_pc = input.readUnsignedShort();
      int catch_type = input.readUnsignedShort();
    }

    int attributes_count = input.readUnsignedShort();
    this.attributes = new Attribute[attributes_count];
    for (int i = 0; i < attributes_count; i++) {
      attributes[i] = attributes[i] = Attribute.constructFromData(input, constantPool);
    }

    //throw new UnimplementedError();
  }

  @Override
  public String toString() {
    return "Code";
  }
}
