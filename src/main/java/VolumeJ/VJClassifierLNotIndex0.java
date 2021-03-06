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
import java.awt.Color;

/**
 * This class implements the Levoy tent classification function with indexing.
 * <ul>
 * <li>{@code index = 0} is not shown (opacity 0).</li>
 * <li>{@code index == 1} is white</li>
 * <li>{@code index > 1} direct to the spectrum LUT ({@code index == 2} is red, {@code index == 128} is green andsoforth)</li>
 * </ul>
 *
 * It can be subclassed for variations on the lookup methods.
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
public class VJClassifierLNotIndex0 extends VJClassifierLevoy
{
        public VJClassifierLNotIndex0()
        {
                super();
                // Name of this classifier.
                description = "Gradient index<>0";
        }
        /**
         * Tell calling program whether this voxel has an interesting index
         * (worthy to do interpolation and gradient calcs on)
         * If the index == 0 (this classifier), the voxel will be skipped.
         * @param v the value of which has to be decided whether it is visiblw or not.
        */
        @Override
        public boolean visible(VJValue v)
        {
                int index = v.index;
                return (index != 0);
        }
        @Override
        public String toLongString()
        {
                return "Levoy ("+((does()==RGB)?"RGB":"grays")+") classifier with >=1 indexing. Voxels more opaque "+
                        " the closer to threshold ("+threshold+") and the higher surface gradient "+
                        " (relative contribution set by deviation). Indexing: index=0, voxel not shown; "+
                        " index=1, voxel shown as gray; else voxel color determined by color LUT.";
        }
        /**
         * Setup a default LUT.
         * <ul>
         * <li>{@code index = 0} is not shown (opacity 0).</li>
         * <li>{@code index == 1} is white</li>
         * <li>{@code index > 1} direct to the spectrum LUT ({@code index == 2} is red, {@code index == 128} is green andsoforth)</li>
         * </ul>
        */
        @Override
        protected void defaultLUT()
        {
				if (nrIndexBits > 0)
				{
						lut = new byte[(int) Math.pow(2, nrIndexBits)*3];// r,g,b
						for (int index = 0; index < (int) Math.pow(2, nrIndexBits); index++)
						{
								if (index == 1)
								{
									// white
									lut[index*3+0] = (byte) 255;
									lut[index*3+1] = (byte) 255;
									lut[index*3+2] = (byte) 255;
								}
								else
								{
									Color c = Color.getHSBColor(index/255f, 1f, 1f);
									lut[index*3+0] = (byte) c.getRed();
									lut[index*3+1] = (byte) c.getGreen();
									lut[index*3+2] = (byte) c.getBlue();
								}
						}
				}
		}
}

