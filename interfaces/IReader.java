package interfaces;

import java.io.IOException;

/**
 * Abstract way to read contents from a file.
 *
 * @author Mihai Burduselu
 */
public interface IReader {
    /**
     * Returns the next boolean in the buffered memory.
     */
    boolean nextBool() throws IOException;

    /**
     * Returns the next integer in the buffered memory.
     */
    int nextInt() throws IOException;

    /**
     * Returns the next long integer in the buffered memory.
     */
    long nextLong() throws IOException;

    /**
     * Returns the next float in the buffered memory.
     */
    float nextFloat() throws IOException;

    /**
     * Returns the next double in the buffered memory.
     */
    double nextDouble() throws IOException;

    /**
     * Returns the next word in the buffered memory.
     */
    String nextWord() throws IOException;

    /**
     * Close the reader.
     *
     * @throws IOException if an I/O exception occurred
     */
    void close() throws IOException;
}
