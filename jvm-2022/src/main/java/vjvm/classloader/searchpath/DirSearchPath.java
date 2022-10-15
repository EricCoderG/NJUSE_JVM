package vjvm.classloader.searchpath;

import vjvm.utils.UnimplementedError;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class DirSearchPath extends ClassSearchPath{

  private final Path searchPath;
  public DirSearchPath(String path) {
    searchPath = FileSystems.getDefault().getPath(path);
  }

  @Override
  public InputStream findClass(String name) {

    File target = searchPath.resolve(name + ".class").toFile();
    FileInputStream ret = null;
    try {
      ret = new FileInputStream(target);
    } catch (FileNotFoundException e) {
      return null;
    }

    return ret;

  }

  @Override
  public void close() throws IOException {

  }
}
