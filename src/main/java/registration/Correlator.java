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
// Source File Name:   Correlator.java

package registration;


public class Correlator
{

    public Correlator()
    {
    }

    public static float[] xcorr(float a[], float b[], int width, int range)
        throws Exception
    {
        int height = a.length / width;
        float xc[] = new float[a.length];
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                float rab = 0.0F;
                float raa = 0.0F;
                float rbb = 0.0F;
                for(int k = -range; k < range; k++)
                {
                    int xi = x + k;
                    if(xi < 0)
                        xi = -xi;
                    else
                    if(xi >= width)
                        xi = 2 * width - xi - 1;
                    float pixela = a[y * width + xi];
                    float pixelb = b[y * width + xi];
                    rab += pixela * pixelb;
                    raa += pixela * pixela;
                    rbb += pixelb * pixelb;
                }

                xc[y * width + x] = rab / ((float)Math.sqrt(raa * rbb) + 1E-13F);
            }

        }

        return xc;
    }
}
