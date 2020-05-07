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
import ij.process.ImageProcessor;
/**
 * This class maps a flow field in color spotnoise field.
 *
 * Copyright (c) 2001-2003, Michael Abramoff. All rights reserved.
 * @author: Michael Abramoff, Image Sciences Institute, University Medical Center Utrecht, Netherlands
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
public class Flow3JColorNoiseMapper extends FlowJMapper
{
	  // spotnoise parameters.
	  public final static double		        chance = 0.01f;
	  public final static double		        minMagnitude = 0.05f;
	  private FlowJSpotNoise 			colornoise;

	  public Flow3JColorNoiseMapper(ImageProcessor impr, float [][][] flow, int axes,
			int maxp, int maxq, double pScaling, double qScaling, double rho)
	  {
			super(impr, flow, axes, maxp, maxq, pScaling, qScaling, rho);
			colornoise = new FlowJSpotNoise((int []) impr.getPixels(), maxp, maxq, FlowJSpotNoise.SIGMA);
	  }
	  public void pixel(int ip, int iq, int ix, int iy, double dx, double dy)
	  /*
			Map the 3D flow in flow at ip, iq into pixels.
			Rho determines the maximal mapped magnitude.
	  */
	  {
			  double r = (double) Math.random();
			  if (r > chance)
			  {
					float [] v;
					if (pScaling == 1 && qScaling == 1)
					{
							v = new float[2];
							v[0] = flow[iy][ix][0]; v[1] = flow[iy][ix][1];
					}
					else
							v = bl(flow, ix, iy, dx, dy);
					float [] pv = new float[3];
					// Only positive flows in y direction (mapped to intensity).
					FlowJFlow.polar3D(pv, v[0], v[2], Math.abs(v[1]));
					if (pv[0]/rho > minMagnitude)
					{
							byte [] rgb = new byte[3];
							FlowJDynamicColor.map3D(rgb, pv[0] / rho, pv[1], pv[2]);
							double rnd = (float) Math.random();
							int rrnd = (int) (rnd * (float) (rgb[0]&0xff));
							int grnd = (int) (rnd * (float) (rgb[1]&0xff));
							int brnd = (int) (rnd * (float) (rgb[2]&0xff));
							colornoise.spot(ip, iq, FlowJSpotNoise.SIGMA
								+ FlowJSpotNoise.SIGMAM*pv[0]/rho, rrnd, grnd, brnd, v);
					}
			  }
	  }
}

