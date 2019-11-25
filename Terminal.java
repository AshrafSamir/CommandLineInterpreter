import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Terminal {

    Terminal t;
    int lasthash = 0;
    String dir = "C:\\Users\\ahmed\\Desktop\\CommandLine";

    private void lastHash(String input){

        int i = 0;
        int startIndex = 0;

        while ((i = input.indexOf('\\',startIndex))!= -1){

            this.lasthash = i;
            startIndex = ++i;
        }
    }

    public void cd( String distinationPath) {
        boolean exist = false;
        int i = 0;
        if (distinationPath.equals("..")){

            if (dir.equals("C:")){
                dir = "\\";
            }
            else if (dir.equals("\\")){

                dir = "\\";
            }

            else{

                lastHash(dir);
                dir = dir.substring(0, lasthash);
            }
        }
        else if (distinationPath.equals("C:")) {

            dir = distinationPath;
        }
        else if (distinationPath.equals("E:")){
            dir = "E:";
        }
        else{

            String tmp = dir + "\\" + distinationPath;
            File file = new File(tmp);
            if (file.exists()){
                dir = tmp;
            }
            else {
                System.out.println("This Directory isn't Exist !!!!");
            }
        }

        System.out.println("Current Directory : " + dir);
    }



    public void cp(String sourcePath, String destinationPath) throws IOException {

        int ind1 = sourcePath.indexOf(":");
        int ind2 = destinationPath.indexOf(":");
        if(ind1 == -1 || ind2 == -1){
            sourcePath = (dir + "\\" + sourcePath);
            destinationPath = (dir + "\\" + destinationPath);
        }

        try{
            File source = new File(sourcePath);
            File dist = new File(destinationPath);

            if (source.exists()){

                if (source.isFile()){

                    if (dist.exists()) {

                        if (dist.isFile()) {
                            Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));
                        }
                        else if (dist.isDirectory()) {
                            String name = new String();
                            for (int i = sourcePath.length() - 1; i >= 0; i--) {
                                if (sourcePath.charAt(i) == '\\') {
                                    name = sourcePath.substring(i);
                                    break;
                                }

                            }
                            destinationPath += name;
                            Files.copy(Paths.get(sourcePath), new FileOutputStream(destinationPath));

                        }
                    }
                    else {
                        System.out.println("This Directory isn't Exist !!!!");
                    }
                }
            }

        }catch (NullPointerException e){

            System.out.println("NULL POINTER EXCEPTION ");
        }

    }





    public void mv(String sourcePath, String destinationPath) throws IOException {

        cp(sourcePath, destinationPath);

        File file = new File(sourcePath);

        if (file.exists()){

            if (file.isFile()){

                    file.delete();
                    System.out.println("File Moved Successfully.....");

            }
        }
        else {
            System.out.println("This Directory isn't Exist !!!!");
        }

    }


    public void rm(String sourcePath) {
        File file = new File(sourcePath);
        if (file.delete()){

            System.out.println("File Deleted Successfuly...");
        }
        else{
            System.out.println("Problem with Deleting the File!!!");
        }
    }



    public void pwd() {

        // Shows The User Current Directory
        System.out.println("Current Directory : " + dir);
    }


    public void ls(ArrayList<String> paths, int flag) throws IOException {
        int size = paths.size();
        StringBuilder content = new StringBuilder();
        FileWriter fileWriter = null;
        int ind2 = 0;
        if (flag != 0) {
            if (size > 0) {
                ind2 = paths.get(paths.size() - 1).indexOf(":");

                if (ind2 != -1) {
                    if (flag == 1) {
                        fileWriter = new FileWriter(dir + "\\" + paths.get(paths.size() - 1));
                    }
                    else {
                        fileWriter = new FileWriter(dir + "\\" + paths.get(paths.size() - 1),true);
                    }
                } else {
                    if (flag == 1) {
                        fileWriter = new FileWriter(paths.get(paths.size() - 1));
                    }
                    else if (flag == 2){ fileWriter = new FileWriter(paths.get(paths.size() - 1),true);}
                }
            }
        }

        if (paths.size() == 0){

            File folder = new File(dir);
            File[] files = folder.listFiles();

            for (File file : files) {

                System.out.println(file.getName() + " ");
            }
        }
        else {
            if (flag == 0) {


                for (int i = 0; i < paths.size(); i++) {


                    File folder = new File(paths.get(i));
                    File[] files = folder.listFiles();
                    for (File file : files) {
                        content.append(file.getName() + "\n");
                        System.out.println(file.getName() + " ");
                    }
                }
            }
            else {

                for (int i = 0; i < paths.size() - 1; i++) {


                    File folder = new File(paths.get(i));
                    File[] files = folder.listFiles();
                    for (File file : files) {
                        content.append(file.getName() + "\n");
                        System.out.println(file.getName() + " ");
                    }
                    content.append("\n\n\n\n");
                    System.out.println("\n\n\n\n");
                }
            }
        }

        String tmp = content.toString();

        if (flag == 1){

            fileWriter.write(tmp);
            fileWriter.close();
        }
        else if (flag == 2){

            fileWriter.append(tmp);
            fileWriter.close();
        }

    }


    public void clear() {
        for (int i = 0; i<100 ; i++){

            System.out.println(" ");
        }

    }


    public void date() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }


    public void help(String[] commands, String[] help) {

        for (int k = 0; k < 15; k++) {
            System.out.println(commands[k] + " : " + help[k]);
        }

    }

    public void args(String[] commands, String[] arguments) {
        for (int k = 0; k < 15; k++) {
            System.out.println(commands[k] + " : " + arguments[k]);
        }
    }


    public void mkdir( String directoryName) {

        File directory = new File(dir + "\\" + directoryName);

        if (!directory.exists()){

                System.out.println("This Directory isn't Exist !!!!");
                return;
        }
        if (directory.mkdir()){

            System.out.println("Directory Created Successfully.......");
        }
        else{

            System.out.println("There Was a Problem Creating The Directory!!!!");
        }
    }


    public void rmdir( String SourcePath) {

        File directory = new File(dir + "\\" + SourcePath);

        if (directory.isDirectory()) {
            if (directory.exists()) {

                if (directory.length() == 0) {

                    if (directory.delete()) {

                        System.out.println("Directory Removed Successfully.......");
                    }
                } else {

                    System.out.println("Directory isn't Empty !!!!!!!!");
                }
            }
            else {
                System.out.println("This Directory isn't Exist !!!!");
            }
        }
    }

    public void cat(ArrayList<String> paths, int flag, String altPath) throws IOException {
        for (String path : paths){
            System.out.println(path);
        }
        int size = paths.size();
        StringBuilder content = new StringBuilder();
        File file;
        FileWriter fileWriter;

        int ind2 = paths.get(paths.size() - 1).indexOf(":");
        if(ind2 != -1){

            fileWriter = new FileWriter(dir + "\\" + paths.get(paths.size() - 1));
        }
        else{
            fileWriter = new FileWriter(paths.get(paths.size() - 1));
        }



        for (int i = 0; i < size - 1; i++){
            int ind1 = paths.get(i).indexOf(":");
            if(ind1 != -1){

                file = new File(dir + "\\" + paths.get(i));
            }
            else{
                file = new File(paths.get(i));
            }

            Scanner scanner = new Scanner(file);
            if (file.exists()){

                if (file.isFile()){

                    while (scanner.hasNextLine()){

                        content.append(scanner.nextLine() + "\n");
                    }
                }
            }
            else {
                System.out.println("No Such File");
                return;
            }




            scanner.close();
        }

        String tmp = content.toString();

        if (flag == 1){

            fileWriter.write(tmp);
            fileWriter.close();
        }
        else if (flag == 2){

            fileWriter.append(tmp);
            fileWriter.close();
        }



        System.out.println(tmp);
    }

    public void more (String path, int flag, String altPath) throws FileNotFoundException {

        File file = new File(dir + "\\" + path);
        Scanner scanner = new Scanner(file);
        int counter = 0;
        if (file.exists()){

            if (file.isFile()){

                while (scanner.hasNextLine()){

                    System.out.println(scanner.nextLine());
                    counter++;
                    if (counter == 10) break;
                }
            }
        }
        scanner.close();
    }


    void set_help(String[] commands, String[] help, String[] arguments) {
        commands[0] = "clear";
        commands[1] = "cd";
        commands[2] = "ls";
        commands[3] = "cp";
        commands[4] = "mv";
        commands[5] = "rm";
        commands[6] = "mkdir";
        commands[7] = "rmdir";
        commands[8] = "cat";
        commands[9] = "more";
        commands[10] = "pwd";
        commands[11] = "date";
        commands[12] = "args";
        commands[13] = "exit";
        commands[14] = "help";

        help[0] = "clear the terminal screen";
        help[1] = "Change the shell working directory.\nChange the current directory to DIR. The default DIR is the value of theHOME shell variable.";
        help[2] = "list directory contents.";
        help[3] = "copy files and directories.";
        help[4] = "move files.";
        help[5] = "remove files or directories.";
        help[6] = "make directories.";
        help[7] = "remove empty directories.";
        help[8] = "concatenate files and print on the standard output.";
        help[9] = "file perusal filter for crt viewing.";
        help[10] = "Print the name of the current working directory.";
        help[11] = "Current date/time.";
        help[12] = "List all command arguments.";
        help[13] = "Stop all.";
        help[14] = "display information about builtin commands.";

        arguments[0] = "it takes no arguments.";
        arguments[1] = "one string the path of the new directory.";
        arguments[2] = "(the arguments are optional) one or more string the directory that would be listed.";
        arguments[3] = "two strings the path of the files.";
        arguments[4] = "two strings the path of the files.";
        arguments[5] = "one string the path of the file.";
        arguments[6] = "one string the path of the directory.";
        arguments[7] = "one string the path of the directory.";
        arguments[8] = "one string or more the path of the file.";
        arguments[9] = "one string the path of the file.";
        arguments[10] = "it takes no arguments.";
        arguments[11] = "it take no arguments.";
        arguments[12] = "one string the name of the commands.";
        arguments[13] = "it takes no arguments.";
        arguments[14] = "it takes no arguments.";
    }

}
