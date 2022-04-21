import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class Main {

    public static String path = "D:" + File.separator + "Games(netology)" + File.separator + "savegames" + File.separator;

    public static void main(String[] args) {
        openZip(path + "save.zip",path);
        System.out.println(openProgress(path + "packed_save1.dat"));
    }

    public static void openZip(String pathFrom, String pathTo) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(pathFrom))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(pathTo + "\\" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }  catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String path) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }

}
