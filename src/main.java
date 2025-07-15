import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        if(args.length==0||args[0].equals("--help")){
            System.out.println("Usage: java Main [--help] [--input <file>] [--output <file>]");
        }
        else if(args[0].equals("-g")){
            System.out.println("Quiet moe");

        }

    }
}