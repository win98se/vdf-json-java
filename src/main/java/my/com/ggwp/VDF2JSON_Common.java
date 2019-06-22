package my.com.ggwp;

import java.io.*;
import org.json.*;

public class VDF2JSON_Common
{
    public static String convertFile2JSON(String file)
    {
        String output="";
        try
        {
            output=com.nosoop.json.VDF.toJSONObject(new JSONTokener(new InputStreamReader(new FileInputStream(new File(file)))), true).toString(4);
        }
        catch(FileNotFoundException | JSONException e)
        {
            System.out.println("[Exception|VDF2JSON_Common.convertFile2JSON] Shit, something happened.\n");
            System.out.println("------------------------------- Stack Trace Begin -------------------------------");
            e.printStackTrace();
            System.out.println("-------------------------------- Stack Trace End --------------------------------\n");
        }
        return(output);
    }

    public static String convertString2JSON(String string)
    {
        String output="";
        try
        {
            output=com.nosoop.json.VDF.toJSONObject(new JSONTokener(string), true).toString(4);
        }
        catch(JSONException e)
        {
            System.out.println("[Exception|VDF2JSON_Common.convertString2JSON] Shit, something happened.");
            System.out.println("------------------------------- Stack Trace Begin -------------------------------");
            e.printStackTrace();
            System.out.println("-------------------------------- Stack Trace End --------------------------------\n");
        }
        return(output);
    }
}