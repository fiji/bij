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
 * VJValue.
 * Contains a voxel value intended to be rendered, which may be int, float or vector etc.
 * This is a more polymorphic way of passing interpolated voxel values around
 * than {@code &}-ing them into an int or using ad hoc float vectors.
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
public class VJValue
{
        public int intvalue;
        public float floatvalue;
        public int index;
        // The k depth of this VJValue.
        public int k;

        public VJValue() {}
        public VJValue(VJValue value)
        { this.floatvalue =  value.floatvalue; this.intvalue = value.intvalue; this.index = value.index; }
        public VJValue(float fl)
        { this.floatvalue =  fl; this.intvalue = (int) floatvalue; this.index = 0; }
        public void setIndex(int index) { this.index = index; }
        public int getIndex() { return index; }
        public int getIntValue() { return intvalue; }
        public float get() { return floatvalue; }
        public String toString() { return ""+floatvalue; }
}
