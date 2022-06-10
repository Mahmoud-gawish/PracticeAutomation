package utils;

import com.microsoft.schemas.office.office.STInsetMode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesReader {

    private  static FileReader reader =null;
    private static String propRoot = "src/main/resource";
    private static Properties p = new Properties();

    public static String getProperty(String propertyFileName, String propertyName){
        String propPath = propRoot +propertyName;

        try {
            reader = new FileReader(propPath);
        }catch (FileNotFoundException e){

            Logger.getLogger("No found in the given path" + propPath);
            e.printStackTrace();
        }

        try {
            p.load(reader);
        }catch (IOException e){
            Logger.getLogger("could not find any properties with the given property name" + propertyName);
            e.printStackTrace();
        }

        return p.getProperty(propertyName);
    }
}
