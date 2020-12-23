package com.github.viktor235.butler.cli;

import picocli.CommandLine;

public class VersionProvider implements CommandLine.IVersionProvider {
    @Override
    public String[] getVersion() {
        String implVersion = App.class.getPackage().getImplementationVersion();
        String version = String.format("${COMMAND-FULL-NAME} %s", implVersion);
        return new String[]{version};
    }
}
