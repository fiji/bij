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
// Source File Name:   Open_Sequence_As_Stack.java

package io;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.gui.GenericDialog;
import ij.io.OpenDialog;
import ij.io.Opener;
import ij.plugin.PlugIn;

public class Open_Sequence_As_Stack
    implements PlugIn
{

    public Open_Sequence_As_Stack()
    {
    }

    public void run(String arg)
    {
        OpenDialog od = new OpenDialog("Open Numbered Sequence of Images As Stack...", arg);
        String directory = od.getDirectory();
        String name = od.getFileName();
        if(name == null)
            return;
        GenericDialog gd = new GenericDialog("Number of images", IJ.getInstance());
        gd.addNumericField("Number: ", imageCount, 0);
        gd.showDialog();
        boolean canceled = gd.wasCanceled();
        if(canceled)
            return;
        imageCount = (int)gd.getNextNumber();
        if(IJ.debugMode)
            IJ.log("SetOpener: " + directory + " (" + imageCount + " files)");
        ImagePlus imp = (new Opener()).openImage(directory, name);
        if(imp == null)
        {
            IJ.error("file not found");
            return;
        }
        ij.measure.Calibration c = imp.getCalibration();
        int type = imp.getType();
        int width = imp.getWidth();
        int height = imp.getHeight();
        ImageStack stack = imp.createEmptyStack();
        ij.process.ImageProcessor ip = imp.getProcessor();
        stack.addSlice(imp.getTitle(), ip);
        StringBuffer sb = new StringBuffer(name);
        int start = 0;
        int end = 0;
        for(int j = sb.length() - 1; j >= 0; j--)
        {
            if(!Character.isDigit(sb.charAt(j)))
                continue;
            if(end == 0)
                end = j;
            start = j;
        }

        String sNumeric = name.substring(start, end + 1);
        String sExtension = name.substring(end, sb.length() - 1);
        String sPrefix = name.substring(0, start);
        Integer iv = new Integer(sNumeric);
        for(int i = 0; i < imageCount; i++)
        {
            String s = Integer.toString(i + iv.intValue() + 0x5f5e100);
            String n = sPrefix + s.substring(s.length() - (end - start) - 1, s.length()) + sExtension;
            ImagePlus imp2 = (new Opener()).openImage(directory, n);
            if(imp2 == null)
            {
                IJ.log(n + ": unable to open");
                continue;
            }
            if(imp2.getWidth() != width || imp2.getHeight() != height)
            {
                IJ.log(n + ": wrong dimensions");
                continue;
            }
            if(imp2.getType() != type)
            {
                IJ.log(n + ": wrong type");
            } else
            {
                String title = imp2.getTitle();
                IJ.showStatus((stack.getSize() + 1) + "/" + i + ": " + title);
                stack.addSlice(title, imp2.getProcessor());
            }
        }

        if(stack.getSize() > 1)
            imp.setStack(null, stack);
        imp.setCalibration(c);
        imp.show();
    }

    private static int imageCount = 20;

}
