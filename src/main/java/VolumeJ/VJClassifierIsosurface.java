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
 * This class implements the classifier for colored isosurface rendering.
 *
 * Copyright (c) 1999-2002, Michael Abramoff. All rights reserved.
 * @author: Michael Abramoff
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
public class VJClassifierIsosurface extends VJClassifier
{
	protected double           threshold;

	public VJClassifierIsosurface()
	{
		threshold = 128;
		description = "isosurface";
	}
	public VJClassifierIsosurface(double threshold)
	{
		this.threshold = threshold;
		description = "isosurface";
	}
	// Set the threshold.
	public void setThreshold(double threshold) { this.threshold = threshold; }
	public double getThreshold() { return threshold; }
	public boolean overThreshold(float value) { return value >= threshold; }
	/**
	 * Classify the voxel value into RGB.
	 * @param v the VJValue, an interpolated value.
	 * @param g the interpolated gradient at the same location
	 * @return an VJAlphaColor
	*/
	public VJAlphaColor alphacolor(VJValue v, VJGradient g) { return null; }
	// Tells renderer that all voxels are visible. Overload as needed.
	public boolean visible(VJValue v) { return true; }
	// Tell calling program that this classifier will return RGB values.
	public int does() { return RGB; }
	/** Tell renderer that this classifier does not have a LUT. */
	public boolean hasLUT() { return false; }
	// Tells the calling program it cannot process indices in the voxel values.
	public boolean doesIndex() { return false; }
	// Tells the calling program it cannot process cutouts (slice faces).
	public boolean doesCutouts() { return false; }
	public String trace(VJValue v, VJGradient g) { return null; }
	public String toLongString()
	{
		return "Isosurface classifier. Shows surfaces where the voxel value is equal to threshold ("+threshold+").";
	}
}

