package vjvm.runtime;

import java.nio.ByteBuffer;
import java.util.Optional;

import lombok.Getter;
import vjvm.utils.UnimplementedError;

/**
 * Slots represents an array of JVM slots as defined in the specification. It
 * supports getting and putting primitive data types, including address.
 */
public class Slots {
  @Getter
  private final ByteBuffer buf;
  @Getter
  private final Class<?>[] types;
  public Slots(int slotSize) {
    // TODO: initialize data structures
    buf = ByteBuffer.allocate(slotSize * 4);
    types = new Class[slotSize];
    //throw new UnimplementedError();
  }

  public int int_(int index) {
    // TODO: return the int at index
    if (types[index] == Integer.class) {
      return buf.getInt(index * 4);
    }
    throw new ClassCastException(String.format("slot %d is not an %s", index, types[index]));
    //throw new UnimplementedError();
  }

  public void int_(int index, int value) {
    // TODO: set the int at index
    types[index] = Integer.class;
    buf.putInt(index * 4, value);
    //throw new UnimplementedError();
  }

  public long long_(int index) {
    // TODO: return the long at index
    if (types[index] == Long.class) {
      return buf.getLong(index * 4);
    }
    throw new ClassCastException(String.format("slot %d is not an %s", index, types[index]));
    //throw new UnimplementedError();
  }

  public void long_(int index, long value) {
    // TODO: set the long at index
    types[index] = Long.class;
    types[index + 1] = null;
    buf.putLong(index * 4, value);
    //throw new UnimplementedError();
  }

  public float float_(int index) {
    // TODO: return the float at index
    if (types[index] == Float.class) {
      return buf.getFloat(index * 4);
    }
    throw new ClassCastException(String.format("slot %d is not an %s", index, types[index]));
    //throw new UnimplementedError();
  }

  public void float_(int index, float value) {
    // TODO: set the float at index
    types[index] = Float.class;
    buf.putFloat(index * 4, value);
    //throw new UnimplementedError();
  }

  public double double_(int index) {
    // TODO: return the double at index
    if (types[index] == Double.class) {
      return buf.getDouble(index * 4);
    }
    throw new ClassCastException(String.format("slot %d is not an %s", index, types[index]));
    //throw new UnimplementedError();
  }

  public void double_(int index, double value) {
    // TODO: set the double at index
    types[index] = Double.class;
    types[index + 1] = null;
    buf.putDouble(index * 4, value);
    //throw new UnimplementedError();
  }

  public byte byte_(int index) {
    // TODO: return the byte at index
    return (byte) int_(index);
    //throw new UnimplementedError();
  }

  public void byte_(int index, byte value) {
    // TODO: set the byte at index
    int_(index, value);
    //throw new UnimplementedError();
  }

  public char char_(int index) {
    // TODO: return the char at index
    return (char) int_(index);
    //throw new UnimplementedError();
  }

  public void char_(int index, char value) {
    // TODO: set the char at index
    int_(index, value);

    //throw new UnimplementedError();
  }

  public short short_(int index) {
    // TODO: return the short at index
    return (short) int_(index);
    //throw new UnimplementedError();
  }

  public void short_(int index, short value) {
    // TODO: set the short at index
    int_(index, value);
    //throw new UnimplementedError();
  }

  public Optional<Object> value(int index) {
    // TODO(optional): return the value at index, or null if there is no value stored at index
    if (types[index] == null) {
      return Optional.empty();
    }
    switch (types[index].getSimpleName()) {
      case "Integer":
        return Optional.of(int_(index));
      case "Float":
        return Optional.of(float_(index));
      case "Double":
        return Optional.of(double_(index));
      case "Long":
        return Optional.of(long_(index));
      case "Byte":
        return Optional.of(byte_(index));
      case "Character":
        return Optional.of(char_(index));
      case "Short":
        return Optional.of(short_(index));
      default:
        throw new ClassCastException(String.format("unexpected type %s", types[index]));
    }
  }


  public int size() {
    // TODO: return the size in the number of slots
    return buf().limit() / 4;
    //throw new UnimplementedError();
  }

  public void copyTo(int begin, int length, Slots dest, int destBegin) {
    // TODO: copy from this:[begin, begin+length) to dest:[destBegin,destBegin+length)
    if (dest == this && destBegin > begin) {
      for (int i = length - 1; i >= 0; i--) {
        types[destBegin + i] = types[begin + i];
        buf.putInt(4 * (destBegin + i), buf.getInt(4 * (begin + i)));
      }
    } else {
      for (int i = 0; i < length; i++) {
        dest.types[destBegin + i] = types[begin + i];
        dest.buf.putInt(4 * (destBegin + i), buf.getInt(4 * (begin + i)));
      }
    }
  }


}
