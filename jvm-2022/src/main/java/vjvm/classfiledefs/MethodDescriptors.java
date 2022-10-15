package vjvm.classfiledefs;

import lombok.var;
import vjvm.utils.UnimplementedError;
import lombok.var;

import java.util.ArrayList;

import static vjvm.classfiledefs.Descriptors.*;

public class MethodDescriptors {
  public static int argc(String descriptor) {
    assert descriptor.startsWith("(");

    // TODO: calculate arguments size in slots
    int argc = 0;

    String parameters = descriptor.substring(1, descriptor.length() - 2);
    ArrayList<String> parameters_descriptors = new ArrayList<>();
    boolean IsNameEnd = true;
    StringBuffer parameter = new StringBuffer();
    for (char c : parameters.toCharArray()){
      if (Descriptors.reference(c) && IsNameEnd) {
        parameter.append(c);
        IsNameEnd = false;
        continue;
      }
      if (!IsNameEnd) {
        parameter.append(c);
        if (c == ';') {
          parameters_descriptors.add(new String(parameter));
          IsNameEnd = true;
          parameter = null;
        }
        continue;
      } else {
        parameters_descriptors.add(c + "");
      }
    }

    for (String a : parameters_descriptors) {
      argc += Descriptors.size(a);
    }

    return argc;
    //throw new UnimplementedError();
  }

  public static char returnType(String descriptor) {
    assert descriptor.startsWith("(");

    var i = descriptor.indexOf(')') + 1;
    assert i < descriptor.length();
    return descriptor.charAt(i);
  }
}
