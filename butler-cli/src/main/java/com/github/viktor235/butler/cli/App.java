package com.github.viktor235.butler.cli;

import com.github.viktor235.butler.Executor;
import com.github.viktor235.butler.config.Config;
import com.github.viktor235.butler.jobs.JobEntity;
import com.github.viktor235.butler.jobs.JobFactory;
import com.github.viktor235.butler.utils.AppException;
import com.github.viktor235.butler.utils.Utils;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@CommandLine.Command(name = "Butler",
        mixinStandardHelpOptions = true,
        versionProvider = com.github.viktor235.butler.cli.VersionProvider.class,
        header = AsciiArt.LOGO,
        description = "This app executes some jobs for You. Just specify the configuration file.",
        descriptionHeading = "%n@|bold,underline Description|@:%n",
        parameterListHeading = "%n@|bold,underline Parameters|@:%n",
        optionListHeading = "%n@|bold,underline Options|@:%n")
public class App implements Callable<Integer> {
    @CommandLine.Parameters(description = "The configuration XML-file. This is the plan of execution.")
    private File configFile;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        System.out.println(AsciiArt.LOGO);

        CliInformer informer = new CliInformer();

        Config config;
        try {
            config = Utils.parseConfigFile(configFile);
        } catch (AppException e) {
            System.out.println(AsciiArt.FAILED);
            informer.reportError("Error while parsing config:", e);
            return 1;
        }

        printConfigInfo(config);

        try {
            JobFactory jobFactory = new JobFactory();
            List<JobEntity> jobs = jobFactory.createJobs(config.getJobs().getJob());
            Executor exec = new Executor(informer);
            exec.execute(jobs);
        } catch (AppException e) {
            System.out.println(AsciiArt.FAILED);
            informer.reportError("Execution interrupted with error:", e);
            return 1;
        }
        System.out.print(AsciiArt.COMPLETED);
        return 0;
    }

    private void printConfigInfo(Config config) {
        String name = config.getName();
        String desc = config.getDescription();
        if (isEmpty(name) && isEmpty(desc))
            return;
        if (isNotEmpty(name)) {
            String toPrint = CommandLine.Help.Ansi.AUTO.string("@|bold Config name:|@ " + name);
            System.out.println(toPrint);
        }
        if (isNotEmpty(desc)) {
            String toPrint = CommandLine.Help.Ansi.AUTO.string("@|bold Description:|@ " + desc);
            System.out.println(toPrint);
        }
        System.out.println();
    }
}
