
import java.io.IOException;
import java.util.Properties;
public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static{
        loadProperties();
    }
    /*
     *Method that returns exact data from properties
     *@param key required data
     * @return data*/
    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }
    /*
     * Loading properties from the fail application.properties*/
    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("app.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PropertiesUtil(){}

}