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
// Source File Name:   DesignMatrixPoly.java

package bijfit;


// Referenced classes of package bijfit:
//            DesignMatrix

public class DesignMatrixPoly extends DesignMatrix
{

    public DesignMatrixPoly(double xs[], int n)
    {
        super.a = new double[xs.length][1];
        for(int j = 0; j < xs.length; j++)
            super.a[j] = fpoly(xs[j], n);

    }

    public DesignMatrixPoly(int k, int n)
    {
        super.a = new double[k][];
        for(int j = 0; j < k; j++)
            super.a[j] = fpoly(j, n);

    }

    protected double[] fpoly(double x, int n)
    {
        double p[] = new double[n];
        p[0] = 1.0D;
        for(int i = 1; i < n; i++)
            p[i] = p[i - 1] * x;

        return p;
    }
}
