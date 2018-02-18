package ru.spbau.egorov.tee.parser;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * This class parses arguments for tee command.
 */
public class Parser {
    public static ParseResult parseCommandLine(@NotNull String[] args) {
        ArrayList<String> filenames = new ArrayList<>();
        boolean appendFlag = false;
        boolean ignoreFlag = false;
        boolean isOptions = true;
        for (String arg : args) {
            if (isOptions) {
                if (arg.charAt(0) == '-') {
                    switch (arg.length()) {
                        case 2: {
                            switch (arg.charAt(1)) {
                                case 'a': {
                                    appendFlag = true;
                                    break;
                                }
                                case 'i': {
                                    ignoreFlag = true;
                                    break;
                                }
                                default: {
                                    filenames.add(arg);
                                    isOptions = false;
                                    break;
                                }
                            }
                            break;
                        }
                        case 3: {
                            if (arg.equals("-ai") || arg.equals("-ia")) {
                                appendFlag = true;
                                ignoreFlag = true;
                            } else {
                                filenames.add(arg);
                                isOptions = false;
                            }
                            break;
                        }
                        default: {
                            filenames.add(arg);
                            isOptions = false;
                            break;

                        }
                    }
                } else {
                    filenames.add(arg);
                    isOptions = false;
                }
            } else {
                filenames.add(arg);
            }
        }
        return new ParseResult(filenames, appendFlag, ignoreFlag);
    }

    public static class ParseResult {
        private ArrayList<String> filenames;
        private boolean isAppend;
        private boolean isIgnore;

        private ParseResult(ArrayList<String> names, boolean appendFlag, boolean ignoreFlag) {
            filenames = names;
            isAppend = appendFlag;
            isIgnore = ignoreFlag;
        }

        public boolean isAppendFlag() {
            return isAppend;
        }

        public boolean isIgnoreFlag() {
            return isIgnore;
        }

        public ArrayList<String> getFilenames() {
            return filenames;
        }
    }
}
