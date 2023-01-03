package fr.saedev.dev.utils;

import com.google.gson.Gson;
import fr.saedev.dev.objects.Affectation;
import fr.saedev.dev.objects.Personne;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ParseJson {

    public static void saveToJson(ArrayList<?> per){
        String json = ListToJson(per);
        if(per.isEmpty()){
            return;
        }
        if (per.get(0) instanceof Personne){
            StringToFile(json, "etu");
        }else if (per.get(0) instanceof Affectation){
            StringToFile(json, "couples");
        }
    }

    public static String getFileContent(String file) throws IOException {
        Path path = Paths.get(file);
        String content = Files.readString(path);
        return content;
    }

    public static Personne[] TextToTabPer(String file){
        Gson gson = new Gson();
        Personne[] per = gson.fromJson(file, Personne[].class);
        return per;
    }

    public static Affectation[] TextToTabAff(String file){
        Gson gson = new Gson();
        Affectation[] per = gson.fromJson(file, Affectation[].class);
        return per;
    }

    public static String ListToJson(ArrayList<?> per){
        Gson gson = new Gson();
        String string = gson.toJson(per);
        return string;
    }

    public static void StringToFile(String json, String name){
        try(Writer fw = new FileWriter(System.getProperty("user.dir") + File.separator + "saves" + File.separator + name + ".json")) {
            fw.write (json);
        } catch (IOException e) {
            System.out.println("Writing error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
