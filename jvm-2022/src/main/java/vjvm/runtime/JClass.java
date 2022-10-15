package vjvm.runtime;


import org.apache.commons.lang3.tuple.Pair;
import vjvm.classloader.JClassLoader;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.FieldInfo;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.attribute.Attribute;
import vjvm.runtime.classdata.constant.ClassConstant;

import java.io.DataInput;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;

import static vjvm.classfiledefs.ClassAccessFlags.*;

public class JClass {
  @Getter
  private final JClassLoader classLoader;
  @Getter
  private final int minorVersion;
  @Getter
  private final int majorVersion;
  @Getter
  private final ConstantPool constantPool;
  @Getter
  private final int accessFlags;
  private FieldInfo[] fields;
  private MethodInfo[] methods;
  @Getter
  private Attribute[] attributes;

  @Getter
  private final int thisClass;
  @Getter
  private final int superClass;
  private final int interfacesCount;
  private int[] interfaces;
  private final int fieldsCount;
  private final int methodsCount;
  private final int attributesCount;
  private int index;
  public ArrayList<Pair<Integer, Byte>> ConstantOrder = new ArrayList<>();

  private String className;

  @SneakyThrows
  public JClass(DataInput dataInput, JClassLoader classLoader) {
    index = 0;
    this.classLoader = classLoader;

    // check magic number
    var magic = dataInput.readInt();
    if (magic != 0xcafebabe) {
      throw new InvalidClassException(String.format(
        "Wrong magic number, expected: 0xcafebabe, got: 0x%x", magic));
    }

    minorVersion = dataInput.readUnsignedShort();
    majorVersion = dataInput.readUnsignedShort();

    constantPool = new ConstantPool(dataInput, this);
    accessFlags = dataInput.readUnsignedShort();


    thisClass = dataInput.readUnsignedShort();
    superClass = dataInput.readUnsignedShort();

    this.className = ((ClassConstant)constantPool.constant(thisClass)).name();
    /**
    System.out.println("class name: " + className);//Output1
    System.out.println("minor version: " + minorVersion);//Output2
    System.out.println("major version: " + majorVersion);//Output3
    System.out.printf("flags: 0x%x\n", accessFlags);//Output4
    **/

    /*throw new UnimplementedError(
        "TODO: you need to construct thisClass, superClass, interfaces, fields, methods, and attributes from dataInput in lab 1.2
          ; remove this for lab 1.1");*/



    String superClassName = ((ClassConstant)constantPool.constant(superClass)).name();
    if ("null".equals(superClassName)) {
      superClassName = "";
    }

    /**
    System.out.println("this class: " + ((ClassConstant)constantPool.constant(thisClass)).name());//Output5
    System.out.println("super class: " + superClassName);//Output6
    System.out.println("constant pool: ");//Output7
    **/

    for (Pair<Integer, Byte> p : ConstantOrder) {
      /**
      System.out.println("#" + p.getKey() + " = " + constantPool.constant(p.getKey()).toString());//Output8 (many)
       **/
    }
    /**
    System.out.println("interfaces:");//Output9
     **/
    interfacesCount = dataInput.readUnsignedShort();
    interfaces = new int[interfacesCount];
    for (int i = 0; i < interfacesCount; i++) {
      interfaces[i] = dataInput.readUnsignedShort();
      /**
      System.out.println(((ClassConstant)constantPool.constant(interfaces[i])).name());//Output10
       **/
    }
    /**
    System.out.println("fields:");//Output11
     **/
    fieldsCount = dataInput.readUnsignedShort();
    fields = new FieldInfo[fieldsCount];
    for (int i = 0; i < fieldsCount; i++) {
      fields[i] = new FieldInfo(dataInput, this);
      /**
      System.out.printf("%s(0x%x): %s\n", fields[i].name(), fields[i].accessFlags(), fields[i].descriptor());//Output12 (many)
       **/
    }
    /**
    System.out.println("methods:");//Output13
     **/
    methodsCount = dataInput.readUnsignedShort();
    methods = new MethodInfo[methodsCount];
    for (int i = 0; i < methodsCount; i++) {
      methods[i] = new MethodInfo(dataInput, this);
      /**
      System.out.printf("%s(0x%x): %s\n", methods[i].name(), methods[i].accessFlags(), methods[i].descriptor());//Output14 (many)
       **/
    }
    attributesCount = dataInput.readUnsignedShort();
    attributes = new Attribute[attributesCount];
    for (int i = 0; i < attributesCount; i++) {
      attributes[i] = Attribute.constructFromData(dataInput, constantPool);
    }
  }

  public MethodInfo findMethod(String name, String descriptor) {
    for (var method : methods) {
      if (method.name().equals(name) && method.descriptor().equals(descriptor))
        return method;
    }

    return null;
  }

  public boolean public_() {
    return (accessFlags & ACC_PUBLIC) != 0;
  }

  public boolean final_() {
    return (accessFlags & ACC_FINAL) != 0;
  }

  public boolean super_() {
    return (accessFlags & ACC_SUPER) != 0;
  }

  public boolean interface_() {
    return (accessFlags & ACC_INTERFACE) != 0;
  }

  public boolean abstract_() {
    return (accessFlags & ACC_ABSTRACT) != 0;
  }

  public boolean synthetic() {
    return (accessFlags & ACC_SYNTHETIC) != 0;
  }

  public boolean annotation() {
    return (accessFlags & ACC_ANNOTATION) != 0;
  }

  public boolean enum_() {
    return (accessFlags & ACC_ENUM) != 0;
  }

  public boolean module() {
    return (accessFlags & ACC_MODULE) != 0;
  }

  public int fieldsCount() {
    return fields.length;
  }

  public FieldInfo field(int index) {
    return fields[index];
  }

  public int methodsCount() {
    return methods.length;
  }

  public MethodInfo method(int index) {
    return methods[index];
  }


  public void ImproveIndex() {
    index++;
  }

  public int getIndex () {
    return index;
  }


  public String name() {
    // TODO: return class name from thisClass
    return className;
  }

}
