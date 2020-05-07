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
 * VJShader is the default null shader.
 *
 * Copyright (c) 2001-2002, Michael Abramoff. All rights reserved.
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
public class VJShader
{
        /** A light (position in viewspace coordinates). */
        protected VJLight       light;
        protected boolean	doBackface;

        /**
         * Create a new null shader.
         */
        public VJShader() {}
        /**
         * Get the Light, if any.
         * @return a Light.
        */
        public VJLight getLight() { return light; }
        /**
         * Set the shader backfacing on or off. Backfacing is that both front- and backfaces
         * (away from the light) are rendered as being lighted.
         * @param doBackface a boolean that that decided whether or not backfacing is turned on.
         */
        public void setBackface(boolean doBackface) { this.doBackface = doBackface; }
        /**
         * Empty shading method uses *normalized* gradient g.
         * @param g the VJGradient containing the normalized gradients in x, y, and z dimensions.
         * @return a VJShade containing the shade.
        */
        public VJShade shade(VJGradient g)
        {
                return null;
        }
        public VJShade getBackground() { return new VJShade(0); }
        public String toString() { return "Empty shader"; }
}

