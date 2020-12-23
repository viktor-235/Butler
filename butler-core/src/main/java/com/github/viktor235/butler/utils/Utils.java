package com.github.viktor235.butler.utils;

import com.github.viktor235.butler.config.Config;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class Utils {
    public static String readFile(Path path) throws AppException {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new AppException("Error while reading file", e);
        }

//        try {
//            return Files.readString(path);
//        } catch (IOException e) {
//            throw new AppException("Error while reading file", e);
//        }
    }

    public static Path writeFile(Path path, String content) throws AppException {
        try {
//            return Files.writeString(path, content);

            return Files.write(path, content.getBytes());
        } catch (IOException e) {
            throw new AppException(String.format("Error while writing file '%s'", path), e);
        } catch (NullPointerException e) {
            throw new AppException(String.format("Error while writing file '%s'. Content is null", path), e);
        }
    }

    public static Config parseConfigFile(File file) throws AppException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Config) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new AppException(String.format("Error while unmarshalling config file '%s'", file), e);
        } catch (IllegalArgumentException e) {
            throw new AppException(String.format("Wrong config file name '%s'", file));
        }
    }

    public static void saveConfigFile(Config config, File file) throws AppException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(config, file);
        } catch (JAXBException e) {
            throw new AppException(String.format("Error while marshalling config file '%s'", file), e);
        }
    }

    public static String extractExceptionMessage(Throwable throwable) {
        if (!(throwable instanceof AppException))
            return ExceptionUtils.getMessage(throwable);

        String msg = throwable.getMessage();
        if (isEmpty(msg))
            msg = throwable.getClass().getTypeName();

        String cause = extractExceptionMessage(throwable.getCause());

        if (isEmpty(cause))
            return msg;
        else
            return msg + "\nCause: " + cause;
    }

    private Utils() {
    }
}
