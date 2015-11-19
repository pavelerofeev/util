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

import org.junit.Test;

import static epavel.util.StringFormatter.$;
import static org.junit.Assert.assertEquals;


public class StringFormatterTest {

    @Test
    public void test$() {
        assertEquals("some good text", $("some {} text", "good"));
        assertEquals("text 123 ", $("text {} ", 123));
        assertEquals("0.4 any", $("{} any", 0.4));
        assertEquals("text with many params", $("text {} many {}", "with", "params"));
        assertEquals("1", $("{}", 1, 2, 3));
        assertEquals("nothing {}", $("nothing {}"));
        assertEquals("no params", $("no params"));
        assertEquals("no params", $("no params", "again", 1));
    }
}
