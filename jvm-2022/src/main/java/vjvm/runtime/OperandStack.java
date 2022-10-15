package vjvm.runtime;

import lombok.var;
import vjvm.utils.UnimplementedError;
import lombok.Getter;

public class OperandStack {  //操作数栈
  @Getter
  private final Slots slots;

  @Getter
  private int top;

  public OperandStack(int stackSize) {
    // TODO: initialize data structures
    slots = new Slots(stackSize);
    top = 0;
    //throw new UnimplementedError();
  }

  public void pushInt(int value) {
    // TODO: push value
    slots.int_(top++, value);
    //throw new UnimplementedError();
  }

  public int popInt() {
    // TODO: pop value
    return slots.int_(--top);
    //throw new UnimplementedError();
  }

  public void pushFloat(float value) {
    // TODO: push value
    slots.float_(top++, value);
    //throw new UnimplementedError();
  }

  public float popFloat() {
    // TODO: pop value
    return slots.float_(--top);
    //throw new UnimplementedError();
  }

  public void pushLong(long value) {
    // TODO: push value
    slots.long_(top, value);
    top += 2;
    //throw new UnimplementedError();
  }

  public long popLong() {
    // TODO: pop value
    top -= 2;
    return slots.long_(top);
    //throw new UnimplementedError();
  }

  public void pushDouble(double value) {
    // TODO: push value
    this.slots.double_(top, value);
    top += 2;
    //throw new UnimplementedError();
  }

  public double popDouble() {
    // TODO: pop value
    top -= 2;
    return slots.double_(top);
    //throw new UnimplementedError();
  }

  public void pushByte(byte value) {
    // TODO: push value
    this.slots.byte_(top++, value);
    //throw new UnimplementedError();
  }

  public byte popByte() {
    // TODO: pop value
    return slots.byte_(--top);
    //throw new UnimplementedError();
  }

  public void pushChar(char value) {
    // TODO: push value
    slots.char_(top++, value);
    //throw new UnimplementedError();
  }

  public char popChar() {
    // TODO: pop value
    return slots.char_(--top);
    //throw new UnimplementedError();
  }

  public void pushShort(short value) {
    // TODO: push value
    slots.short_(top++, value);
    //throw new UnimplementedError();
  }

  public short popShort() {
    // TODO: pop value
    return slots.short_(--top);
    //throw new UnimplementedError();
  }

  public void pushSlots(Slots slots) {
    // TODO: push slots
    slots.copyTo(0, slots.size(), this.slots, top);
    top += slots.size();
    //throw new UnimplementedError();
  }

  public Slots popSlots(int count) {
    // TODO: pop count slots and return
    assert top >= count;

    var ret = new Slots(count);
    top -= count;
    slots.copyTo(top, count, ret, 0);

    return ret;
    //throw new UnimplementedError();
  }

  public void clear() {
    // TODO: pop all slots
    top = 0;
    //throw new UnimplementedError();
  }
}
