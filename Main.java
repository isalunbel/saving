import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.io.FileInputStream;
import java.util.zip.ZipOutputStream;
import java.io.File;
import java.lang.NullPointerException;


public class Main {
    public static void main(String[] args) {

        GameProgress game1 = new GameProgress(100, 3, 5, 125.4);
        GameProgress game2 = new GameProgress(80, 2, 3, 78.9);
        GameProgress game3 = new GameProgress(120, 4, 7, 185.7);


        String savePath = "/Users/elizavetagilyarevskaya/Desktop/Games/savegames";
        try {
            FileOutputStream fileOutputStream1 = new FileOutputStream("/Users/elizavetagilyarevskaya/Desktop/Games/savegames/game1.dat");
            ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
            objectOutputStream1.writeObject(game1);
            objectOutputStream1.close();

            FileOutputStream fileOutputStream2 = new FileOutputStream("/Users/elizavetagilyarevskaya/Desktop/Games/savegames/game2.dat");
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(fileOutputStream2);
            objectOutputStream2.writeObject(game2);
            objectOutputStream2.close();

            FileOutputStream fileOutputStream3 = new FileOutputStream("/Users/elizavetagilyarevskaya/Desktop/Games/savegames/game3.dat");
            ObjectOutputStream objectOutputStream3 = new ObjectOutputStream(fileOutputStream3);
            objectOutputStream3.writeObject(game3);
            objectOutputStream3.close();

            System.out.println("Сохранение игровых прогрессов выполнено успешно");
        } catch (IOException e) {
            e.printStackTrace();

        }

        String zipPath = "savegames.zip";


        File savegamesFolder = new File(savePath);
        File[] files = savegamesFolder.listFiles();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipPath);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);


            for (File file : files) {
                if (file.isFile()) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zipOutputStream.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, length);
                    }

                    fileInputStream.close();
                }
            }

            zipOutputStream.closeEntry();
            zipOutputStream.close();

            System.out.println("Архивирование выполнено успешно");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (File file : files) {
            if (file.isFile()) {
                if (!file.getName().equals("savegames.zip")) {
                    file.delete();
                }
            }
        }

        System.out.println("Удаление файлов сохранений выполнено успешно");

    }
}