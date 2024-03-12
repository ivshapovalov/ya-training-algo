package ru.algo.ya;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContestTask {
    public static final InputStream DEFAULT_STDIN = System.in;
    public static final PrintStream DEFAULT_STDOUT = System.out;
    public ByteArrayInputStream testIn;
    public ByteArrayOutputStream testOut = new ByteArrayOutputStream();

    protected String getOutput() {
        return testOut.toString();
    }

    protected void provideConsoleInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    protected String getFileContent(Class clazz, String path) throws FileNotFoundException {

        StringBuilder data = new StringBuilder();
        final File classFilePath =
                new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath());
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(classFilePath.getParentFile()
                             .getParentFile().getPath() + path))) {
            String line = reader.readLine();
            while (line != null) {
                data.append(line + "\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    protected String getFileContent(String path) throws FileNotFoundException {

        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            while (line != null) {
                data.append(line + "\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    protected String getResourceFile(Class clazz, String fileName) {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String resourcePath = resourceDirectory.toFile().getAbsolutePath();
        String[] folders = clazz.getCanonicalName().split("\\.");
        StringBuilder fullPath = new StringBuilder(resourcePath + "/");
        for (int i = 0; i < folders.length; i++) {
            fullPath.append(folders[i]).append("/");
        }
        String filePath = fullPath.append(fileName).toString();
        return filePath;
    }

    @BeforeEach
    public void setUpStreams() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void rollbackChanges() {
        System.setOut(DEFAULT_STDOUT);
        System.setIn(DEFAULT_STDIN);
    }

    protected void assertStringEqualsIgnoreLineSeparators(String expected, String actual) {
        expected = expected.replace("\r\n", "\n");
        actual = actual.replace("\r\n", "\n");
        assertEquals(expected, actual);
    }
}
