package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String file1Path, String file2Path, String destPath) {
        Path path1 = Paths.get(file1Path);
        Path path2 = Paths.get(file2Path);
        Path dest = Paths.get(destPath);

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(path1);
            } catch (NoSuchFileException e) {
                throw new RuntimeException("Failed to read file: " + file1Path + " - " + e.getClass().getSimpleName(), e);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read file: " + file1Path, e);
            }
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(path2);
            } catch (NoSuchFileException e) {
                throw new RuntimeException("Failed to read file: " + file2Path + " - " + e.getClass().getSimpleName(), e);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read file: " + file2Path, e);
            }
        });

        return future1.thenCombine(future2, (content1, content2) -> {
            String combinedContent = content1 + "\n" + content2;
            try {
                Files.writeString(dest, combinedContent, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            } catch (IOException e) {
                throw new RuntimeException("Failed to write to file: " + destPath, e);
            }
            return "Files successfully merged into " + destPath;
        }).exceptionally(ex -> {
            System.out.println("Exception occurred: " + ex.getMessage());
            return null;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        String file1 = "src/main/resources/file1.txt";
        String file2 = "src/main/resources/file2.txt";
        String destFile = "src/main/resources/mergedFile.txt";

        CompletableFuture<String> result = App.unionFiles(file1, file2, destFile);

        result.thenAccept(System.out::println).join();
        // END
    }
}

