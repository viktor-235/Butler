package com.github.viktor235.butler.cli;

import picocli.CommandLine;

public class AsciiArt {

    // Font Name: Small Slant

    public static final String LOGO = "\n" +
            "   ___       __  __       \n" +
            "  / _ )__ __/ /_/ /__ ____\n" +
            " / _  / // / __/ / -_) __/\n" +
            "/____/\\_,_/\\__/_/\\__/_/   \n";

    public static final String COMPLETED = CommandLine.Help.Ansi.AUTO.string("@|green \n" +
            "   _____                __    __         __\n" +
            "  / ___/__  __ _  ___  / /__ / /____ ___/ /\n" +
            " / /__/ _ \\/  ' \\/ _ \\/ / -_) __/ -_) _  / \n" +
            " \\___/\\___/_/_/_/ .__/_/\\__/\\__/\\__/\\_,_/  \n" +
            "               /_/                         \n|@");

    public static final String FAILED = CommandLine.Help.Ansi.AUTO.string("@|red \n" +
            "    ____     _ __       __\n" +
            "   / __/__ _(_) /__ ___/ /\n" +
            "  / _// _ `/ / / -_) _  / \n" +
            " /_/  \\_,_/_/_/\\__/\\_,_/  \n|@");
}
