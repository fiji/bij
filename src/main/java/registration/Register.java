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
package registration;

import ij.gui.PlotWindow;

/**
 * This class interfaces the VolumeJ package to a ImageJ plugin.
 *
 * Copyright (c) 1999-2002, Michael Abramoff. All rights reserved.
 * @author: Michael Abramoff
 *
 * Small print:
 * Permission to use, copy, modify and distribute this version of this software or any parts
 * of it and its documentation or any parts of it ("the software"), for any purpose is
 * hereby granted, provided that the above copyright notice and this permission notice
 * appear intact in all copies of the software and that you do not sell the software,
 * or include the software in a commercial package.
 * The release of this software into the public domain does not imply any obligation
 * on the part of the author to release future versions into the public domain.
 * The author is free to make upgraded or improved versions of the software available
 * for a fee or commercially only.
 * Commercial licensing of the software is available by contacting the author.
 * THE SOFTWARE IS PROVIDED "AS IS" AND WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS, IMPLIED OR OTHERWISE, INCLUDING WITHOUT LIMITATION, ANY
 * WARRANTY OF MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.
 */
public class Register
{
    protected float reference[];
    protected float mask[];
    protected float estimate[];
    protected int width;

    public float[] register(float a[])
    {
        System.out.println("method register not defined in class Register");
        return null;
    }

    public static void plotParameters(String title, float parameters[][])
    {
        float xValues[] = new float[parameters.length];
        for(int i = 0; i < xValues.length; i++)
            xValues[i] = (float)i + (float)1;

        float yValues[] = new float[parameters.length];
        for(int i = 0; i < yValues.length; i++)
            yValues[i] = parameters[i][0];

        PlotWindow pw = new PlotWindow("x-deltas ".concat(String.valueOf(String.valueOf(title))), "slice", "x-translation (pixels)", xValues, yValues);
        pw.draw();
        yValues = new float[parameters.length];
        for(int i = 0; i < yValues.length; i++)
            yValues[i] = parameters[i][1];

        pw = new PlotWindow("y-deltas ".concat(String.valueOf(String.valueOf(title))), "slice", "y-deltas (pixels)", xValues, yValues);
        pw.draw();
        if(parameters[0].length >= 3)
        {
            yValues = new float[parameters.length];
            for(int i = 0; i < yValues.length; i++)
                yValues[i] = parameters[i][2];

            pw = new PlotWindow("rotations ".concat(String.valueOf(String.valueOf(title))), "slice", "rotation (degrees)", xValues, yValues);
            pw.draw();
        }
    }
}
