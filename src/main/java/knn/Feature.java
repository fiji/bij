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
// Source File Name:   Feature.java

package knn;

import bijnum.BIJstats;

import java.util.Enumeration;
import java.util.Vector;

public class Feature
{

    public Feature(String name, float vector[])
    {
        this.vector = vector;
        this.name = name;
    }

    public Feature(String name, byte vector[])
    {
        float fv[] = new float[vector.length];
        for(int i = 0; i < vector.length; i++)
            fv[i] = vector[i];

        this.vector = fv;
        this.name = name;
    }

    public void unitvar()
    {
        vector = BIJstats.unitvar(vector);
    }

    public float[] toVector()
    {
        return vector;
    }

    public String toString()
    {
        return name;
    }

    public int length()
    {
        return vector.length;
    }

    public static float[][] toMatrix(Vector features)
    {
        float matrix[][] = new float[features.size()][];
        int i = 0;
        for(Enumeration e = features.elements(); e.hasMoreElements();)
            matrix[i++] = (float[])(float[])e.nextElement();

        return matrix;
    }

    public static Vector add(Vector features, Feature newfeatures[])
    {
        for(int i = 0; i < newfeatures.length; i++)
            features.addElement(newfeatures[i]);

        return features;
    }

    public static Vector add(Vector features, Feature newfeature)
    {
        features.addElement(newfeature);
        return features;
    }

    public static Vector add(Vector features, Vector newfeatures)
    {
        for(Enumeration e = newfeatures.elements(); e.hasMoreElements(); features.addElement(e.nextElement()));
        return features;
    }

    public static String[] toString(Vector features)
    {
        String sa[] = new String[features.size()];
        int i = 0;
        for(Enumeration e = features.elements(); e.hasMoreElements();)
            sa[i++] = (String)e.nextElement();

        return sa;
    }

    public float vector[];
    public String name;
}
