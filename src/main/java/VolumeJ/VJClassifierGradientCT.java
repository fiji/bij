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
 * This class implements the Gradient classification function with indexing
 * for CT scans optimized.
 * <pre>
 * Index = 0 is invisible.
 * Index = 128 semi-transparent blue.
 * Index = 255 white.
 * </pre>
 * This can be used to index and indicate structures in CT scans
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
public class VJClassifierGradientCT extends VJClassifierLNotIndex0
{
        public VJClassifierGradientCT()
        {
                super();
                // Name of this classifier.
                description = "Gradient CT (indexed)";
        }
        /**
         * Classify the (interpolated) voxel value and gradient magnitude into a alpha and rgb-value.
         * Index 0 is not visible, index 128 is semi-transparent.
         * @param v the interpolated value at this location.
         * @param g the gradient at this location
        */
        public VJAlphaColor alphacolor(VJValue v, VJGradient g)
        {
                int intensity = v.intvalue;
                int index = v.index;
                // Code magnitude into bits.
                int igradient = (int) (g.getmag() * fractionMagnitude) & maskMagnitude;
                // make all of it fit into 16 bits.
                int entry = (igradient << nrIntensityBits) | intensity;
                if (index == 0) return new VJAlphaColor(); // invisible.
                else if (index == 128) return new VJAlphaColor(0.1f,  // semi-transparent.
                        (lut[index*3+0]&0xff),
                        (lut[index*3+1]&0xff),
                        (lut[index*3+2]&0xff));
                else return new VJAlphaColor(opacityTable[entry],  // normal Levoy.
                        (lut[index*3+0]&0xff),
                        (lut[index*3+1]&0xff),
                        (lut[index*3+2]&0xff));
        }
        /**
         * Fill the lut if not initialised from elsewhere.
         * First entry is not shown, next entry (1) is white.
         * Rest is filled with a spectrum.
        */
        protected void defaultLUT()
        {
                if (nrIndexBits > 0)
                {
                        lut = new byte[(int) Math.pow(2, nrIndexBits)*3];// r,g,b
                        for (int index = 0; index < (int) Math.pow(2, nrIndexBits); index++)
                        {
                                if (index == 1 || index == 255)
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
        public String toLongString()
        {
                return "Levoy ("+((does()==RGB)?"RGB":"grays")+") classifier optimized for CT scans. "+
                "Uses indexing. Voxel colors  are: index=0 invisible, index=128 semi-transparent blue, "+
                "index=255 gray.";
        }
}

