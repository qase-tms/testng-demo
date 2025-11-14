package utils;

import java.io.IOException;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public static void zipFolder(String sourceDirPath, String zipFilePath) throws IOException {
        Path zipPath = Paths.get(zipFilePath);
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(zipPath))) {
            Path pp = Paths.get(sourceDirPath);

            Files.walk(pp)
                 .filter(path -> !Files.isDirectory(path))
                 .forEach(path -> {
                     ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                     try {
                         zs.putNextEntry(zipEntry);
                         Files.copy(path, zs);
                         zs.closeEntry();
                     } catch (IOException e) {
                         System.err.println(e);
                     }
                 });
        }
    }
}
