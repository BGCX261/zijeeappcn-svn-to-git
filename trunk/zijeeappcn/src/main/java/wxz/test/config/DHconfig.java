package wxz.test.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class DHconfig {
    private static Configuration config = null;
    private static String configfile = "jdbc.properties";
    static {
        try {
            config = new PropertiesConfiguration(configfile);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String key) {
        return config.getString(key);
    }

    public static String[] getStringArray(String key) {
        return config.getStringArray(key);
    }

    public static void main(String[] args) {
        String[] strArray = DHconfig.getStringArray("jdbc.driverClassName");
        System.out.println(strArray[0]);
    }
}
