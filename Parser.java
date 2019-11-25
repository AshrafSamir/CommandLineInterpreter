import javax.crypto.spec.PSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser {

    ArrayList<String> args = new ArrayList<>(); // Will be filled by arguments extracted by parse method
    ArrayList<String> dirFiles = new ArrayList<>();
    ArrayList<Integer> argsIndex = new ArrayList<>();
    String[] commands = new String[15];
    String[] arguments = new String[15];
    String[] help = new String[15];
    ArrayList<String> arr = new ArrayList<>();

    String cmd; // Will be filled by the command extracted by parse method

    public Parser() {
    }

    // Returns true if it was able to parse user input correctly. Otherwise false
    // In case of success, it should save the extracted command and arguments
    // to args and cmd variables
    // It should also print error messages in case of too few arguments for a commands
    // eg. “cp requires 2 arguments

    public boolean parse(String input, Terminal t) throws IOException {
        int i = 0;
        t.set_help(commands, help, arguments);
        i = input.indexOf('|');
        if (i != -1 ){

            split(input);
        }
        else
        {
            arr.add(input);
        }

        for (String tmp : arr) {

            extractArgs(tmp);

            switch (cmd) {

                case "cp":
                    if (args.size() != 2) {
                        System.out.println("Wrong Number of Args");
                    } else {
                        try {

                            t.cp(args.get(0), args.get(1));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        args.clear();
                        argsIndex.clear();

                    }
                    break;
                case "ls":

                    int x1 = 0;
                    int index1 = 0;
                    String tmp2 = new String();
                    if ((index1 = input.indexOf('>')) != -1 ){ x1 = 1; tmp2 = input.substring(index1, input.indexOf("txt", index1) + 3);}
                    else if ((index1 = input.indexOf(">>")) != -1) {x1 = 2; tmp2 = input.substring(index1, input.indexOf("txt", index1) + 3);}
                    t.ls(args, x1);
                    argsIndex.clear();
                    args.clear();
                    break;
                case "exit":
                    return false;

                case "cd":
                    if (args.size() != 1) {
                        System.out.println("Wrong Number of Args");
                    } else {

                        t.cd(args.get(0));
                    }
                    args.clear();
                    argsIndex.clear();
                    break;
                case "clear":
                    if (args.size() != 0) {

                        System.out.println("This Command doesn't take any argument !!!!");

                    } else {
                        t.clear();
                    }
                    args.clear();
                    args.clear();
                    break;
                case "mkdir":
                    if (args.size() != 1) {

                        System.out.println("This Command takes one argument !!!!");

                    } else {
                        t.mkdir(args.get(0));
                    }
                    argsIndex.clear();
                    args.clear();
                    break;
                case "pwd":
                    if (args.size() != 0) {

                        System.out.println("This Command doesn't take any argument !!!!");

                    } else {
                        t.pwd();
                    }
                    argsIndex.clear();
                    args.clear();
                    break;

                case "rmdir":
                    if (args.size() != 1) {

                        System.out.println("This Command takes one argument !!!!");

                    } else {
                        t.rmdir(args.get(0));
                    }
                    argsIndex.clear();
                    args.clear();
                    break;

                case "mv":
                    if (args.size() != 2) {

                        System.out.println("This Command takes two argument !!!!");

                    } else {
                        t.mv(args.get(0), args.get(1));
                    }
                    argsIndex.clear();
                    args.clear();
                    break;

                case "rm":
                    if (args.size() != 1) {

                        System.out.println("This Command takes one argument !!!!");

                    } else {
                        t.rm(args.get(0));
                    }
                    argsIndex.clear();
                    args.clear();
                    break;

                case "args":
                    if (args.size() != 0) {

                        System.out.println("This Command doesn't take any argument !!!!");

                    } else {
                        t.args(commands, arguments);
                    }
                    argsIndex.clear();
                    args.clear();
                    break;

                case "date":
                    if (args.size() != 0) {

                        System.out.println("This Command doesn't take any argument !!!!");

                    } else {
                        t.date();
                    }
                    argsIndex.clear();
                    args.clear();
                    break;

                case "help":
                    if (args.size() != 0) {

                        System.out.println("This Command doesn't take any argument !!!!");

                    } else {
                        t.help(commands, help);
                    }
                    argsIndex.clear();
                    args.clear();
                    break;

                case "more":
                    if (args.size() != 1) {

                        System.out.println("This Command takes at Least one argument !!!!");
                    } else {
                        int x = 0;
                        int index = 0;
                        String tmp1 = new String();
                        if ((index = input.indexOf('>')) != -1 ){ x = 1; tmp1 = input.substring(index, input.indexOf("txt", index) + 3);}
                        else if ((index = input.indexOf(">>")) != -1) {x = 2; tmp1 = input.substring(index, input.indexOf("txt", index) + 3);}

                        t.more(args.get(0), x, tmp1);
                        args.clear();
                        argsIndex.clear();
                    }


                case "cat":
                    if (args.size() < 1) {

                        System.out.println("This Command takes at Least one argument !!!!");
                    } else {
                        int x = 0;
                        int index = 0;
                        String tmp1 = new String();
                        if ((index = input.indexOf('>')) != -1 ){ x = 1; tmp1 = input.substring(index, input.indexOf("txt", index) + 3);}
                        else if ((index = input.indexOf(">>")) != -1) {x = 2; tmp1 = input.substring(index, input.indexOf("txt", index) + 3);}
                        t.cat(args, x, tmp1);
                        args.clear();
                        argsIndex.clear();
                    }
            }

        }
        arr.clear();
        args.clear();
        argsIndex.clear();
        return true;
    }
    public String getCmd(){

        return this.cmd;
    }
    public ArrayList<String> getArguments(){

        return this.args;
    }

    public void split(String input){
        int i = 0;
        int startIndex = 0;
        String tmp = new String();
        while ((startIndex = input.indexOf('|', i+1)) != -1){

            tmp =  input.substring(i, startIndex);
            arr.add(tmp);
            i = startIndex;

        }

        tmp = input.substring(i+1);
        arr.add(tmp);

    }


    private void extractArgs(String input){

        int i = 0;
        numOfArgs(input);
        if (argsIndex.size() == 0){
            cmd = input;
        }
        else{

            i = input.indexOf(" ");
            cmd = input.substring(0, i);
        }

        String tmp  = null;
        for (int index = 0; index < argsIndex.size(); index++){

            if (argsIndex.size() - index == 1){

                tmp = input.substring(argsIndex.get(index)+1).replaceAll("\"", "");
                this.args.add(tmp);
                break;
            }
            if (input.substring(argsIndex.get(index)+1, argsIndex.get(index+1)).equals(">") || input.substring(argsIndex.get(index)+1, argsIndex.get(index+1)).equals(">>")){
                System.out.println(input.substring(argsIndex.get(index)+1, argsIndex.get(index+1)));
                continue;
                }
            tmp = input.substring(argsIndex.get(index)+1, argsIndex.get(index+1)).replaceAll("\"", "");
            this.args.add(tmp);
        }


    }

   /* private int checkLongPath(String input){

        boolean flag = false;
        int firstPlace,secondPlace = 0;

        firstPlace = input.indexOf('"');
        secondPlace = input.indexOf('"', firstPlace+1);

        if (firstPlace != -1 && secondPlace != -1){

            args.add(input.substring(firstPlace+1, secondPlace));
            return secondPlace + 1;
        }
        return 0;
    }*/

    private void numOfArgs(String input){
        int i = 0;
        boolean flag = false;
        while (i < input.length()-1){

            if (input.charAt(i) == ' '){
                this.argsIndex.add(i);
                for (int tmp : argsIndex){
                    System.out.println(tmp);
                }
                ++i;
                if (input.charAt(i) == '\"') {
                    ++i;
                    while (input.charAt(i) != '\"') {

                        ++i;
                    }
                }


                /*if (input.charAt(i - 1) != '\"' && flag == true){ i++; flag = false; continue; }*/
                //this.argsIndex.add(i);
            }
            /*else if (input.charAt(i) == '"'){

                int tmp = checkLongPath(input.substring(i));
                for (int k = 0; k< argsIndex.size(); k++){
                    System.out.println(argsIndex.get(k));
                }
                if (tmp != 0){

                    i += tmp;
                }
                else{
                    i++;
                }
                continue;
            }*/
            i++;

        }
    }
}