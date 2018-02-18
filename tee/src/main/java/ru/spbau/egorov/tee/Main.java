package ru.spbau.egorov.tee;


import ru.spbau.egorov.tee.parser.Parser;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Parser.ParseResult res = Parser.parseCommandLine(args);
        try {
            Tee tee = new Tee(res.getFilenames(), res.isAppendFlag(), res.isIgnoreFlag());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                System.out.println(line);
                tee.printToAllStreams(line);
            }
        } catch (IOException e) {
            if (!res.isIgnoreFlag()) {
                throw e;
            }
        }
    }
}
