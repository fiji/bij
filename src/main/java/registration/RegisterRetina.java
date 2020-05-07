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
// Source File Name:   RegisterRetina.java

package registration;

import bijfit.Maximum;
import bijnum.BIJmatrix;
import bijnum.BIJmi;
import ij.IJ;
import numericalMethods.calculus.minimizing.nmPowell;
import volume.Equalize;
import volume.Hessian;

// Referenced classes of package registration:
//            RegisterMI, SearchMI, Register

public class RegisterRetina extends RegisterMI
{

    public RegisterRetina(float reference[], int width, float mask[])
    {
        super.mask = mask;
        IJ.showStatus("Registering...equalizing reference image");
        IJ.showStatus("Registering...equalizing");
        reference = Equalize.sliding(reference, width, 0.0F, 25, true);
        IJ.showStatus("Registering...finding blobs");
        float blobs[] = Hessian.det(reference, width, 10D);
        float mblobs[] = BIJmatrix.mulElements(blobs, mask);
        IJ.showStatus("Registering...estimating disk location");
        referenceDisk = Maximum.findSubpixel(mblobs, width);
        makeMaskedNaN(reference, super.mask);
        super.minmaxref = BIJmatrix.minmax(reference);
        super.width = width;
        super.reference = reference;
    }

    public float[] register(float a[])
    {
        IJ.showStatus("Registering...equalizing");
        a = Equalize.sliding(a, super.width, 0.0F, 25, true);
        IJ.showStatus("Registering...finding disk");
        float blobs[] = Hessian.det(a, super.width, 10D);
        float mblobs[] = BIJmatrix.mulElements(blobs, super.mask);
        IJ.showStatus("Registering...estimating translation");
        float approx[] = Maximum.findSubpixel(mblobs, super.width);
        approx = BIJmatrix.sub(referenceDisk, approx);
        IJ.showStatus("Registering...masking");
        makeMaskedNaN(a, super.mask);
        float minmaxa[] = BIJmatrix.minmax(a);
        float min = Math.min(minmaxa[0], super.minmaxref[0]);
        float max = Math.max(minmaxa[1], super.minmaxref[1]);
        float scale = BIJmi.getNiceScale(min, max);
        IJ.showStatus("Registering...maximizing MI");
        SearchMI function = new SearchMI(min, max, scale, super.reference, a, super.width);
        double p[] = new double[function.getDoubleArrayParameterLength()];
        p[0] = approx[0];
        p[1] = approx[1];
        p[2] = 0.0D;
        function.setDoubleArrayParameter(p, 0);
        nmPowell.search(p, 0.001D, function, function, 10, true);
        IJ.showStatus("Registering...registering slice");
        super.estimate = new float[p.length];
        for(int i = 0; i < p.length; i++)
            super.estimate[i] = (float)p[i];

        return super.estimate;
    }

    public static float[] featureBlobs(float image[], int width)
    {
        return Hessian.det(image, width, 10D);
    }

    public static float[] featureEdges(float image[], int width)
    {
        return Hessian.largest(image, width, 1.5D);
    }

    public static void makeMaskedNaN(float image[], float mask[])
    {
        for(int i = 0; i < image.length; i++)
            if(mask[i] == 0.0F)
                image[i] = (0.0F / 0.0F);

    }

    protected float referenceDisk[];
}
