/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Pavel Erofeev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package epavel.util;

import com.google.common.jimfs.Jimfs;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * @author Pavel Erofeev
 */
public class FileUtilsTest {

    private FileSystem fs1 = Jimfs.newFileSystem();
    private FileSystem fs2 = Jimfs.newFileSystem();

    private Path path = fs1.getPath("/some");

    private String dirA = "other/directory/tree";
    private Path dirAPath = path.resolve(dirA);

    private String dirB = "empty";
    private Path dirBPath = path.resolve(dirB);

    private String fileA = "other/fileA";
    private Path fileAPath = path.resolve(fileA);

    private String fileB = "other/directory/tree/fileB";
    private Path fileBPath = path.resolve(fileB);

    @Before
    public void setUp() throws IOException {
        Files.createDirectories(dirAPath);
        Files.createDirectories(dirBPath);
        Files.write(fileAPath, "content of file A".getBytes());
        Files.write(fileBPath, "content of file B".getBytes());
    }

    @Test
    public void testDeleteDirectories() {
        FileUtils.deleteDirectories(path);

        assertFalse(Files.exists(dirAPath));
        assertFalse(Files.exists(dirBPath));
        assertFalse(Files.exists(fileAPath));
        assertFalse(Files.exists(fileBPath));
    }

    @Test
    public void testCopyDirectoriesSameFS() throws IOException {
        Path dest = fs1.getPath("target");

        FileUtils.copyDirectories(path, dest);

        assertTrue(Files.exists(dest.resolve(dirA)));
        assertTrue(Files.exists(dest.resolve(dirB)));
        assertTrue(Files.exists(dest.resolve(fileA)));
        assertTrue(Files.exists(dest.resolve(fileB)));

        String ca = new String(Files.readAllBytes(dest.resolve(fileA)));
        assertEquals("content of file A", ca);

        String cb = new String(Files.readAllBytes(dest.resolve(fileB)));
        assertEquals("content of file B", cb);
    }

    @Test
    public void testCopyDirectoriesDifferentFS() throws IOException {
        Path dest = fs2.getPath("target");

        FileUtils.copyDirectories(path, dest);

        assertTrue(Files.exists(dest.resolve(dirA)));
        assertTrue(Files.exists(dest.resolve(dirB)));
        assertTrue(Files.exists(dest.resolve(fileA)));
        assertTrue(Files.exists(dest.resolve(fileB)));

        String ca = new String(Files.readAllBytes(dest.resolve(fileA)));
        assertEquals("content of file A", ca);

        String cb = new String(Files.readAllBytes(dest.resolve(fileB)));
        assertEquals("content of file B", cb);
    }
}