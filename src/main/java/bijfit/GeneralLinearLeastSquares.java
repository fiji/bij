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
// Source File Name:   GeneralLinearLeastSquares.java

package bijfit;

import Jama.Matrix;
import bijnum.BIJutil;

// Referenced classes of package bijfit:
//            DesignMatrixLinear, DesignMatrix

public class GeneralLinearLeastSquares
{

    public GeneralLinearLeastSquares()
    {
    }

    public static float[] fit(float b[], DesignMatrix dm)
    {
        double db[] = new double[b.length];
        for(int k = 0; k < b.length; k++)
            db[k] = b[k];

        double Aa[][] = dm.getMatrix();
        Matrix a = new Matrix(Aa);
        Matrix x = a.solve((new Matrix(db, 1)).transpose());
        double xs[][] = x.transpose().getArray();
        float coords[] = new float[xs[0].length];
        for(int l = 0; l < coords.length; l++)
            coords[l] = (float)xs[0][l];

        return coords;
    }

    public static double[] fit(double db[], DesignMatrix dm)
    {
        double Aa[][] = dm.getMatrix();
        Matrix a = new Matrix(Aa);
        Matrix x = a.solve((new Matrix(db, 1)).transpose());
        double xs[][] = x.transpose().getArray();
        double coords[] = new double[xs[0].length];
        for(int l = 0; l < coords.length; l++)
            coords[l] = xs[0][l];

        return coords;
    }

    public static float[][] fit(float b[][], DesignMatrix dm)
    {
        float coordinates[][] = new float[b.length][];
        for(int j = 0; j < b.length; j++)
            coordinates[j] = fit(b[j], dm);

        return coordinates;
    }

    public static void test()
    {
        float x[] = {
            -2F, -1F, 0.0F, 1.0F, 2.0F
        };
        float y[] = {
            -2F, 0.0F, 0.0F, 1.0F, 2.0F
        };
        DesignMatrix dm = new DesignMatrixLinear(x);
        float params[] = fit(y, dm);
        System.out.println(" x: " + BIJutil.toString(x) + " y: " + BIJutil.toString(y) + "a's: " + BIJutil.toString(params));
    }
}
