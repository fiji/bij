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
// Source File Name:   Features.java

package knn;

import bijnum.BIJmatrix;

import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package knn:
//            Feature

public class Features extends Vector
{

    public Features()
    {
    }

    public Features(float fts[][], int startindex, String name)
    {
        for(int i = startindex; i < fts.length; i++)
        {
            Feature f = new Feature(name + " " + i, fts[i]);
            addElement(f);
        }

    }

    public float[][] toMatrix()
    {
        float matrix[][] = new float[size()][];
        int i = 0;
        for(Enumeration e = elements(); e.hasMoreElements();)
            matrix[i++] = (float[])(float[])e.nextElement();

        return matrix;
    }

    public void add(Feature newfeatures[])
    {
        for(int i = 0; i < newfeatures.length; i++)
            addElement(newfeatures[i]);

    }

    public void add(Feature newfeature)
    {
        addElement(newfeature);
    }

    /**
     * @deprecated Method add is deprecated
     */

    public void add(Vector newfeatures)
    {
        for(Enumeration e = newfeatures.elements(); e.hasMoreElements(); addElement(e.nextElement()));
    }

    public float[][] subset(int indices[])
    {
        float matrix[][] = new float[indices.length][];
        for(int i = 0; i < indices.length; i++)
        {
            Feature f = (Feature)(Feature)elementAt(indices[i]);
            matrix[i] = f.toVector();
        }

        return BIJmatrix.transpose(matrix);
    }

    public Features sample(int indices[])
    {
        Features nf = new Features();
        int i = 0;
        Feature f;
        float nv[];
        for(Enumeration e = elements(); e.hasMoreElements(); nf.add(new Feature(f.toString(), nv)))
        {
            f = (Feature)(Feature)e.nextElement();
            i++;
            float v[] = f.toVector();
            nv = BIJmatrix.subset(v, indices);
        }

        return nf;
    }

    public int length()
    {
        Feature f = (Feature)(Feature)elementAt(0);
        return f.toVector().length;
    }

    public String toString(int i)
    {
        Feature f = (Feature)(Feature)elementAt(i);
        return f.toString();
    }

    public float[] toVector(int i)
    {
        Feature f = (Feature)(Feature)elementAt(i);
        return f.toVector();
    }
}
