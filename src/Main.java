import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();

        String projectDir = System.getProperty("user.dir") + "/src";

        // Директории src, res, savegames и temp
        createDir(projectDir + "/Games/src", log);
        createDir(projectDir + "/Games/res", log);
        createDir(projectDir + "/Games/savegames", log);
        createDir(projectDir + "/Games/temp", log);

        // Директории main и test
        createDir(projectDir + "/Games/src/main", log);
        createDir(projectDir + "/Games/src/test", log);

        // Директории drawables, vectors и icons
        createDir(projectDir + "/Games/res/drawables", log);
        createDir(projectDir + "/Games/res/vectors", log);
        createDir(projectDir + "/Games/res/icons", log);

        // Файлы Main.java, Utils.java и temp.txt
        createFile(projectDir + "/Games/src/main/Main.java", log);
        createFile(projectDir + "/Games/src/main/Utils.java", log);
        createFile(projectDir + "/Games/temp/temp.txt", log);

        writeLog(projectDir + "/Games/temp/temp.txt", log);

        GameProgress progress1 = new GameProgress(100, 2, 10, 15.5);
        GameProgress progress2 = new GameProgress(80, 3, 12, 20.0);
        GameProgress progress3 = new GameProgress(50, 5, 15, 25.5);

        String savePath1 = "D:/Portfolio/java-dz/src/Games/savegames/save1.dat";
        String savePath2 = "D:/Portfolio/java-dz/src/Games/savegames/save2.dat";
        String savePath3 = "D:/Portfolio/java-dz/src/Games/savegames/save3.dat";

        saveGame(savePath1, progress1);
        saveGame(savePath2, progress2);
        saveGame(savePath3, progress3);

        List<String> filesToZip = List.of(savePath1, savePath2, savePath3);
        String zipFilePath = "D:/Portfolio/java-dz/src/Games/savegames/saves.zip";
        zipFiles(zipFilePath, filesToZip);

//        deleteFiles(filesToZip);
    }

    private static void createDir(String path, StringBuilder log) {
        File dir = new File(path);

        if (dir.mkdirs()) {
            log.append("Directory created: ").append(path).append('\n');
        } else {
            log.append("Failed to create directory: ").append(path).append('\n');
        }
    }

    private static void createFile(String path, StringBuilder log) {
        File file = new File(path);

        try {
            if (file.createNewFile()) {
                log.append("File created: ").append(path).append('\n');
            } else {
                log.append("Failed to create file: ").append(path).append('\n');
            }
        } catch (IOException e) {
            log.append("Error creating file: ").append(path).append(" - ").append(e.getMessage()).append('\n');
        }
    }

    private static void writeLog(String path, StringBuilder log) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(log.toString());
        } catch (IOException e) {
            System.out.println("Error writing log to file: " + e.getMessage());
        }
    }

    private static void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println("Filed to save game progress: " + e.getMessage());
        }
    }

    private static void zipFiles(String zipFilesPath, List<String> filePaths) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilesPath))) {
            for (String filePath : filePaths) {
                try (FileInputStream fis = new FileInputStream(filePath)) {
                    ZipEntry zipEntry = new ZipEntry(new File(filePath).getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int length;

                    while ((length = fis.read(buffer)) >= 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();
                } catch (IOException e) {
                    System.out.println("Failed to add file to zip: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to create zip file: " + e.getMessage());
        }
    }

    private static void deleteFiles(List<String> filePaths) {
        for (String filePath : filePaths) {
            File file = new File(filePath);

            if (file.delete()) {
                System.out.println("Deleted file: " + filePath);
            } else {
                System.out.println("Failed to delete file: " + filePath);
            }
        }
    }
}