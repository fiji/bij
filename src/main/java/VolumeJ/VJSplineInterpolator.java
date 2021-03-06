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
package VolumeJ;

/**
 * This class implements cubic spline interpolation
 * and interpolation of gradients.
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
public abstract class VJSplineInterpolator extends VJInterpolator
{
        /**
         * Cubic spline interpolation.
         * Also known as Catmull-Rom spline
         * Calculate {@code sample = h(x) a + h(x) b + h(x) c + h(x) d},
         * where {@code a,b,c,d} are the values at the sample locations,
         * and {@code x (-2,2)} is the location between {@code a} and {@code d}.
         * @param x {0,1} the location at which to interpolate the sample
         * @param a the value of the sample at -1
         * @param b the value of the sample at 0
         * @param c the value of the sample at 1
         * @param d the value of the sample at 2
         * @return the interpolated value
         */
        private static double cubicspline(double x, double a, double b, double c, double d)
        {
                return h(-1.0-x) * a + h(-x) * b + h(1.0-x) * c + h(2.0-x) * d;
        }
        /**
         * Calculate spline h(x) value.
         *              h(-x)                           x<0
         * h(x) =       3/2x^3-5/2x^2+1                 0 <= x < 1
         *              -1/2x^3 + 5/2x^2 - 4x + 2       1 <= x < 2
         *              0                               otherwise
         *  @param x the value for which to calculate h.
         *  @return the h value as above.
         */
        private static double h(double x)
        {
                if (x < 0) x = -x;
                if (x >= 0 && x < 1)
                        return Math.pow(x, 3) * 1.5 - 2.5 * Math.pow(x, 2) + 1.0;
                else if (x >= 1 && x < 2)
                        return - Math.pow(x, 3) * 0.5 + 2.5 * Math.pow(x, 2) - 4.0 * x + 2.0;
                else
                        return 0;
        }
}
