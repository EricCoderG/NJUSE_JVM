package vjvm.runtime.classdata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.attribute.Attribute;
import vjvm.runtime.classdata.constant.ClassConstant;
import vjvm.runtime.classdata.constant.NameAndTypeConstant;
import vjvm.runtime.classdata.constant.UTF8Constant;
import vjvm.utils.UnimplementedError;

import java.io.DataInput;

import static vjvm.classfiledefs.FieldAccessFlags.*;

@RequiredArgsConstructor
public class FieldInfo {
  @Getter
  private final short accessFlags;
  @Getter
  private final String name;
  @Getter
  private final String descriptor;
  private final Attribute[] attributes;
  @Getter
  private JClass jClass;

  @SneakyThrows
  public FieldInfo(DataInput dataInput, JClass jClass) {
    this.jClass = jClass;
    this.accessFlags = dataInput.readShort();
    int nameIndex = dataInput.readUnsignedShort();
    int descriptorIndex = dataInput.readUnsignedShort();
    this.name = ((UTF8Constant)jClass.constantPool().constant(nameIndex)).value();
    this.descriptor = ((UTF8Constant)jClass.constantPool().constant(descriptorIndex)).value();
    int attributes_count = dataInput.readUnsignedShort();
    this.attributes = new Attribute[attributes_count];
    for (int i = 0; i < attributes_count; i++) {
      attributes[i] = attributes[i] = Attribute.constructFromData(dataInput, jClass.constantPool());
    }

    //throw new UnimplementedError("TODO: get field info from constant pool");
  }

  public int attributeCount() {
    return attributes.length;
  }

  public Attribute attribute(int index) {
    return attributes[index];
  }

  public boolean public_() {
    return (accessFlags & ACC_PUBLIC) != 0;
  }

  public boolean private_() {
    return (accessFlags & ACC_PRIVATE) != 0;
  }

  public boolean protected_() {
    return (accessFlags & ACC_PROTECTED) != 0;
  }

  public boolean static_() {
    return (accessFlags & ACC_STATIC) != 0;
  }

  public boolean final_() {
    return (accessFlags & ACC_FINAL) != 0;
  }

  public boolean transient_() {
    return (accessFlags & ACC_TRANSIENT) != 0;
  }

  public boolean synthetic() {
    return (accessFlags & ACC_SYNTHETIC) != 0;
  }

  public boolean enum_() {
    return (accessFlags & ACC_ENUM) != 0;
  }
}
