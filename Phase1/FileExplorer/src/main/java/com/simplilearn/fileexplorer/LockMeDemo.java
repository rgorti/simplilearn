package com.simplilearn.fileexplorer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LockMeDemo {
    boolean exit;
    public static void main(String[] args) {
        LockMeDemo demo = new LockMeDemo();
        demo.runMenu();
    }
    public void runMenu() {
        printHeader();
        while (!exit) {
            List<String> menu = Arrays.asList("List Files","Add File","Delete a File",
                                                "Search for a File");
            printMenu(menu);
            int choice = getInput();
            performAction(choice);
        }
    }
    private void printHeader(){
        System.out.println("#------------------------------------------#");
        System.out.println("|           WELCOME TO LOCKME DEMO         |");
        System.out.println("|          FILE EXPLORER APPLICATION        |");
        System.out.println("#------------------------------------------#");
    }

    private void printMenu(List<String> list) {
        FileUtil.print("\nPlease make a selection");
        FileUtil.printList(list);
        FileUtil.print("\nPress 0 to Exit the Application");
    }

    private int getInput() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        char shutDown = 'n';
        while (choice < 0 || choice > 4) {
            try {
                System.out.println("Enter your choice (1 to 4):");
                choice = Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {
                FileUtil.printError("Invalid Selection. Please Try Again",e);
            }
        }
        return choice;
    }

    private void performAction(int choice){
        switch(choice){
            // List Files for a given directory
            case 0 : {
                exit = true;
                System.out.println("\nThank you for using the Application");
                break;
            }

            case 1 : {
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Please provide the directory path to list");
                String dirPath = sc1.nextLine();
                FileExplorer.listFiles(dirPath);
                break;
            }
            // Add File to a directory
            case 2 : {
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Provide directory path");
                String dirPath = sc1.nextLine();
                System.out.println("Provide the File name to be added");
                String fileName = sc1.nextLine();
                boolean isValid = FileUtil.isValidPath(dirPath);
                if (isValid) {
                    String path = dirPath+"/"+fileName;
                    FileExplorer.addFile(path);
                }
                else
                    System.out.println("Please provide valid directory name");
                break;
            }
            case 3 : {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the Path of the file");
                String dirPath = sc.nextLine();
                boolean isValid = FileUtil.isValidPath(dirPath);
                System.out.println("Enter the file to be deleted..");
                String fileName = sc.nextLine();
                if (isValid){
                    String path = dirPath+"/"+fileName;
                    FileExplorer.deleteFile(path);
                }
                else
                    System.out.println("Please provide valid directory name");
                break;
            }
            case 4 : {
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Enter the file to be searched.. " );
                String fileName = sc1.nextLine();
                System.out.println("Enter the Path to search for... ");
                String dirPath = sc1.nextLine();
                System.out.println("Searching.... Please wait");
                List<String> searchResult = FileExplorer.findFile(fileName, dirPath);
               // List<String> searchResult = FileManager.searchFile(fileName, dirPath);
                break;
            }
            default :{
                System.out.println("An unknown error has occured");
                break;
            }

        }

    }
}
