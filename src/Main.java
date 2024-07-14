import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
}