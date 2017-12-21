package utils;

import interfaces.IReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

public final class FileReader implements IReader {
    private static final String DELIMITER = " ";

    private java.io.FileReader fileReader;
    private BufferedReader bufferedReader;
    private StringTokenizer stringTokenizer;

    public FileReader(final String filePath) throws FileNotFoundException {
        fileReader = new java.io.FileReader(filePath);
        bufferedReader = new BufferedReader(fileReader);
    }

    private void readLine() throws IOException {
        stringTokenizer = new StringTokenizer(
                bufferedReader.readLine(),
                DELIMITER);
    }

    private String nextToken() throws IOException {
        if (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
            readLine();
        }

        return stringTokenizer.nextToken();
    }

    @Override
    public boolean nextBool() throws IOException {
        return Boolean.parseBoolean(nextToken());
    }

    @Override
    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    @Override
    public long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    @Override
    public float nextFloat() throws IOException {
        return Float.parseFloat(nextToken());
    }

    @Override
    public double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    @Override
    public String nextWord() throws IOException {
        return nextToken();
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
        fileReader.close();
    }
}
