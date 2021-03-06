/*-
 * #%L
 * Bio-medical imaging in Java.
 * %%
 * Copyright (C) 1997 - 2020 Michael Abramoff and Fiji developers.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileAndString.java

package io;

import java.io.File;

public class FileAndString
    implements Comparable
{

    public FileAndString(File file, String string)
    {
        this.file = file;
        this.string = string;
    }

    public int compareTo(Object o)
    {
        return string.compareTo(((FileAndString)o).string);
    }

    public File getFile()
    {
        return file;
    }

    public String getString()
    {
        return string;
    }

    File file;
    String string;
}
