package ru.spbau.egorov.tee;

import java.io.*;
import java.util.ArrayList;

public class Tee {

    private boolean isIgnore;

    private ArrayList<String> filenames = new ArrayList<>();

    public Tee(ArrayList<String> names, boolean appendFlag, boolean ignoreFlag) throws IOException {
        isIgnore = ignoreFlag;
        filenames = names;
        for (String filename : filenames) {
            try (FileWriter fw = new FileWriter(filename, appendFlag)) {
            } catch (IOException e) {
                if (!isIgnore)
                    throw e;
            }
        }
    }

    public void printToAllStreams(String line) throws IOException {
        for (String filename : filenames) {
            try (FileWriter fw = new FileWriter(filename);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(line);
            } catch (IOException e) {
                if (!isIgnore)
                    throw e;
            }
        }
    }
}
