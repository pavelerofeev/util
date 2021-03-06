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


import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * String formatter. Replaces {@code {} } elements in the template with parameters.
 *
 * @author Pavel Erofeev
 */
public final class StringFormatter {

    private StringFormatter() {}

    public static String $(String template, Object... params) {
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for (Object p : params) {
            int i = template.indexOf("{}", k);
            if (i == -1)
                break;
            sb.append(template.substring(k, i)).append(p);
            k = i + 2;
        }
        sb.append(template.substring(k));
        return sb.toString();
    }

    public static String stackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
