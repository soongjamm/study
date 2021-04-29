package com.soongjamm.startboot.godOfJava.collections;

import java.io.*;
import java.util.Properties;

public class PropertiesSample {
    public static void main(String[] args) {
        PropertiesSample propertiesSample = new PropertiesSample();
        propertiesSample.saveAndLoadProperties();
        propertiesSample.saveAndLoadPropertiesXML();
    }

    public void saveAndLoadPropertiesXML() {
        try {
            String fileName = "text.xml";
            File propertiesFile = new File(fileName);
            FileOutputStream fos = new FileOutputStream(propertiesFile);
            Properties prop = new Properties();
            prop.setProperty("Writer", "Soongjamm king");
            prop.setProperty("WriterHome", "Somewhere");
            prop.storeToXML(fos, "Basic XML Proprty file");
            fos.close();

            FileInputStream fis = new FileInputStream(propertiesFile);
            Properties propLoaded = new Properties();
            propLoaded.loadFromXML(fis);
            System.out.println(propLoaded);
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAndLoadProperties() {
        try {
            String fileName = "test.properties";
            File propertiesFile = new File(fileName);
            FileOutputStream fos = new FileOutputStream(propertiesFile);
            Properties prop = new Properties();
            prop.setProperty("Writer", "Soongjamm kim");
            prop.setProperty("WriterHome", "somewhere");
            prop.store(fos, "Basic Properties file");
            fos.close();

            FileInputStream fis = new FileInputStream(propertiesFile);
            Properties propLoaded = new Properties();
            propLoaded.load(fis);
            fis.close();
            System.out.println(propLoaded);
            System.out.println(System.getProperty("user.dir"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
