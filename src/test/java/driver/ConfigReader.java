package driver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigReader {
  private static final Properties properties = new Properties();

  static {
    try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("application-test.properties")) {
      if (input == null) {
        throw new RuntimeException("Не найден application-test.properties");
      }
      String encoding = System.getProperty("properties.encoding", "UTF-8");
      try (InputStreamReader reader = new InputStreamReader(input, encoding)) {
        properties.load(reader);
      }
    } catch (IOException e) {
      throw new RuntimeException("Ошибка загрузки application-test.properties", e);
    }
  }

  public static String getProperty(String key) {
    return properties.getProperty(key);
  }
}
