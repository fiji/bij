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
package volume;

/**
 * This is a 1D Difference of Gaussian convolution kernel.
 *
 * DoG(x) = 1 / Math.sqrt(2 Math.PI * sigma^2) e^(-x^2 / 2 sigma^2) - 1 / Math.sqrt(2 Math.PI * (1.6 sigma)^2) e^(-x^2 / 2 (1.6 sigma)^2)
 *
 * @author: Michael Abramoff, (c) 1999-2003 Michael Abramoff. All rights reserved.
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
public class DoG extends Gaussian
{
        /**
        * Create a Difference of Gaussian kernel from standard deviation sigma.
        * @param sigma, the standard deviation of the associated probability function of the Gaussian function.
        */
        public DoG(double sigma)
        {
                this.sigma = sigma;
                if (sigma != 0)
                {
                        // Extra wide kernel to accept widest gaussian.
                        int width = (int) (6 * (sigma*1.6) + 1);
                        if (width % 2 == 0) width++;
                        halfwidth = width / 2;
                        k = new double[halfwidth*2+1];
                        for (int l = -halfwidth; l <= halfwidth; l++)
                                    k[l + halfwidth] = function(l);
                }
                else
                        halfwidth = 0;
        }
        /**
        * Compute Difference of Gaussian function at x.
        */
        protected double function(double x)
        {
                return 1.0 / (Math.sqrt(2 * Math.PI) * (sigma)) * Math.exp(-x * x / (2 * Math.pow(sigma, 2)))
                        - 1.0 / (Math.sqrt(2 * Math.PI) * sigma * 1.6) * Math.exp(-x * x / (2 * Math.pow(sigma * 1.6, 2)));
        }
        public String toString() { return "DoG 1D "+sigma;  }
}
