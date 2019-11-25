import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class CommandLine {


    public static void main(String[] args) throws IOException {


        Scanner myScan = new Scanner(System.in);
        Parser p = new Parser();
        Terminal t = new Terminal();
        String input;
        boolean flag = true;

        while(flag)
        {
            input = myScan.nextLine();
            flag = p.parse(input, t);
        }

    }

}
