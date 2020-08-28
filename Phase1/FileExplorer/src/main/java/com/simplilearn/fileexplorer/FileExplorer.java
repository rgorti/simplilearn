package com.simplilearn.fileexplorer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileExplorer {
    public static void listFiles(String dirPath) {
        try {
            File folder = new File(dirPath);
            File[] files = folder.listFiles();
            if (files.length == 0)
                FileUtil.print("There are no files in the given "+dirPath);
            else {
                FileUtil.print(files.length+" files in the given "+dirPath);
                for (File file : files) {
                    FileUtil.print(file.getAbsolutePath());
                }
            }
        } catch (NullPointerException e) {
            FileUtil.printError("The given File Path does not exist",e);
        }
    }

    /**
     * This Fails with AccessDenied Exception on Windows

    public static List<String> searchFile(String fileName, String dirPath) {
        List<String> result = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(dirPath))) {

            result = walk.map(x -> x.toString())
                    .filter(f -> f.contains(fileName))
                    .collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }*/

    public static List<String> findFile(String fileName, String dirPath) {
        List<String> searchList = new ArrayList<String>();
        try {
            File path = new File(dirPath);
            File[] list = path.listFiles();
            if (list != null) {
                for (File file : list) {
                    if (file.isDirectory()) {
                        findFile(fileName, file.getPath());
                    } else if (fileName.equalsIgnoreCase(file.getName())) {
                        FileUtil.print("File Found at: " + file.getAbsolutePath());
                        searchList.add(file.getPath());
                    }
                }
            }
            return searchList;
        } catch (NullPointerException e) {
            FileUtil.printError("Failed to list Files",e);
        }
        return searchList;
    }

    public static void deleteFile(String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        }
        catch(NoSuchFileException e) {
            FileUtil.printError("No such file/directory exists",e);
        }
        catch(DirectoryNotEmptyException e) {
            FileUtil.printError("Directory is not empty.",e);
        }
        catch(IOException e) {
            FileUtil.printError("Invalid permissions.",e);
        }
        FileUtil.print("Deletion successful.");
    }

    public static void addFile(String filePath) {
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            //writer.write("Hello World !!");
            FileUtil.print("File Successfully Created!!!");
        } catch(NoSuchFileException e) {
            FileUtil.printError("No such file/directory exists",e);
        } catch (IOException e) {
            FileUtil.printError("Failed to create file.....",e);

        }
    }
}
