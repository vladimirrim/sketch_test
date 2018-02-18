package ru.spbau.egorov.tee.parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseCommandLineOnlyFilenames() {
        ArrayList<String> args = new ArrayList<>();
        args.add("-a.txt");
        args.add("--");
        args.add("a.txt");
        Parser.ParseResult parseResult= Parser.parseCommandLine(args.toArray(new String[args.size()]));
        assertEquals(args,parseResult.getFilenames());
        assertFalse(parseResult.isIgnoreFlag());
        assertFalse(parseResult.isAppendFlag());
    }

    @Test
    public void parseCommandLineOneOption() {
        ArrayList<String> args = new ArrayList<>();
        ArrayList<String> res = new ArrayList<>();
        args.add("-a");
        args.add("--");
        res.add("--");
        args.add("a.txt");
        res.add("a.txt");
        Parser.ParseResult parseResult= Parser.parseCommandLine(args.toArray(new String[args.size()]));
        assertEquals(res,parseResult.getFilenames());
        assertFalse(parseResult.isIgnoreFlag());
        assertTrue(parseResult.isAppendFlag());
    }

    @Test
    public void parseCommandLineTwoOptionsTwoFlags() {
        ArrayList<String> args = new ArrayList<>();
        ArrayList<String> res = new ArrayList<>();
        args.add("-a");
        args.add("-i");
        args.add("a.txt");
        res.add("a.txt");
        Parser.ParseResult parseResult= Parser.parseCommandLine(args.toArray(new String[args.size()]));
        assertEquals(res,parseResult.getFilenames());
        assertTrue(parseResult.isIgnoreFlag());
        assertTrue(parseResult.isAppendFlag());
    }

    @Test
    public void parseCommandLineTwoOptionsOneFlag() {
        ArrayList<String> args = new ArrayList<>();
        ArrayList<String> res = new ArrayList<>();
        args.add("-ai");
        args.add("a.txt");
        res.add("a.txt");
        Parser.ParseResult parseResult= Parser.parseCommandLine(args.toArray(new String[args.size()]));
        assertEquals(res,parseResult.getFilenames());
        assertTrue(parseResult.isIgnoreFlag());
        assertTrue(parseResult.isAppendFlag());
    }
}