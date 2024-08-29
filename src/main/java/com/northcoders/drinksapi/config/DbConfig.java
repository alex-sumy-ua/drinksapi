package com.northcoders.drinksapi.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DbConfig {
    private Properties config = null;

    public Properties getConfig() {
        return this.config;
    }

    public DbConfig() {
        Properties properties = new Properties();
        String configFilePath= "src/main/resources/application.properties";
        File ConfigFile= new File(configFilePath);

        try (FileInputStream configFileReader = new FileInputStream(ConfigFile)) {

            properties.load(configFileReader);

            if (!properties.containsKey("db.url") ||
                    !properties.containsKey("db.username") ||
                    !properties.containsKey("db.password")) {
                System.out.println("Application Properties didn't contain required properties.");
                this.config = null;
                return;
            }

            this.config = properties;

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO exception!");
        }
    }
}