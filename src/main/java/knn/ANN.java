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
// Source File Name:   ANN.java

package knn;


public class ANN
{

    public ANN()
    {
    }

    public static native int bdtree(float af[][], int i);

    public static native int getvote(float af[], int i);

    public static native int annkPriSearch(float af[], int i, int ai[], float af1[], double d);

    public static native int annkEstimates(float af[], float af1[][], int i, int j, double d, float af2[]);

    public static float[] estimate(float querypts[][], float trueclasses[], int nrclasses, int k, double epsilon)
        throws Exception
    {
        if(querypts == null || trueclasses == null)
            throw new IllegalArgumentException("ANN.estimate(): arguments are null");
        float estimates[] = new float[querypts.length];
        int error = annkEstimates(trueclasses, querypts, k, nrclasses, epsilon, estimates);
        if(error < 0)
            throw new Exception("ANN.estimate(): error in annkPriSearch " + error);
        else
            return estimates;
    }

    static 
    {
        System.loadLibrary("ann_java");
    }
}
