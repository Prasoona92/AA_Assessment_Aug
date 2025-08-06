package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigFileReader.class);
    private static final Properties properties = new Properties();
    private static final String CONFIG_PATH = System.getProperty("user.dir") + "/src/main/config.properties";

    static {
        loadProperties();
    }

    /**
     * Loads the configuration properties from the config file.
     */
    private static void loadProperties() {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_PATH)) {
            properties.load(fileInputStream);
            logger.info("Successfully loaded configuration from {}", CONFIG_PATH);
        } catch (IOException e) {
            logger.error("Failed to load config.properties from {}", CONFIG_PATH, e);
            throw new RuntimeException("Error loading config.properties from: " + CONFIG_PATH, e);
        }
    }

    /**
     * Retrieves the property value for the given key.
     *
     * @param key The property key.
     * @return The property value or null if the key is not found.
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            logger.warn("Property '{}' not found in config.properties", key);
        } else {
            logger.debug("Retrieved property: {} = {}", key, value);
        }
        return value;
    }


    /**
     * Retrieves the UI base URL from the config file.
     *
     * @return The UI base URL.
     */
    public static String getBaseUrl() {
        return getProperty("ui.base.url.test");
    }
}
