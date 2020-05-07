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
package FlowJ;
import volume.Kernel1D;


/*
	This class implements the real and imaginary parts of a complex deconvolution kernel.
	Based on Fleet and Jepson, 1990, Computation of Component Image Velocity from
	Local Phase Information, IJCV

	(c) 1999, Michael Abramoff
*/
public class Demodulation extends Kernel1D
{
	public Demodulation(float k0, boolean isReal)
	{
		  halfwidth = 2;             // on both sides!
		  k = new double[halfwidth*2+1];
		  if (isReal)
		  {
				k[0] = Math.cos(2*k0)/12;
				k[1] = -8*Math.cos(k0)/12;
				k[2] = 0;
				k[3] = 8*Math.cos(k0)/12;
				k[4] = -Math.cos(2*k0)/12;
		  }
		  else  // Imaginary kernel
		  {
				k[0] = Math.sin(2*k0)/12;
				k[1] = -8*Math.sin(k0)/12;
				k[2] = 0;
				k[3] = -8*Math.sin(k0)/12;
				k[4] = Math.sin(2*k0)/12;
		  }

	}
}
