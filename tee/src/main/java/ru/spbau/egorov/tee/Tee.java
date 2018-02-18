package ru.spbau.egorov.tee;

import java.io.*;
import java.util.ArrayList;

/**
 * This class writes String to all given files.
 */
public class Tee {

    private boolean isIgnore;

    private ArrayList<String> filenames = new ArrayList<>();

    /**Initializes isIgnore and filenames fields, clear files if appendFlag is true.
     * @param names are given filenames.
     * @param appendFlag is true if need to append to files.
     * @param ignoreFlag is true if need to ignore exceptions.
     * @throws IOException
     */
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

    /**
     * Writes String to the files.
     * @param line is string to write.
     * @throws IOException if isIgnore flag is false.
     */
    public void printToAllStreams(String line) throws IOException {
        for (String filename : filenames) {
            try (FileWriter fw = new FileWriter(filename,true);
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
