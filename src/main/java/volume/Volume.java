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
 * This is an abstract super-class to implement volumes.
 * Volumes are 3-D ordered structures that are indexed by three-parameters. The values in a volume can consist
 * of scalars, vectors or Objects.
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
public abstract class Volume extends Object implements java.io.Serializable
{
        /** Dimensions of the volume. */
        protected int           width, height, depth;
        // the boundary of validity after filtering
        protected int           edge;
        /** Anisotropy measure of the volume. */
        protected double        aspectx, aspecty, aspectz;

        /**
         * @return the width of the volume.
         */
        public int getWidth() { return width; }
        /**
         * @return the height of the volume.
         */
        public int getHeight() { return height; }
        /**
         * @return the depth of the volume.
         */
        public int getDepth() { return depth; }
        /**
         * @param x, y, z the position in the volume you want the value of.
         * @return the value of the volume at x,y,z.
         */
        public abstract Object get(int x, int y, int z);
        /**
         * Set the voxel at x,y,z to value.
         * @param value a value suitable for this volume.
         * @param x, y, z the position in the volume.
         */
        public abstract void set(Object value, int x, int y, int z);
        /**
         * Set the aspect ratio of this volume.
         * @param aspectx, aspecty, aspectz the x,y,z aspect rations.
         */
        public void setAspects(double aspectx, double aspecty, double aspectz)
        { this.aspectx = aspectx; this.aspecty = aspecty; this.aspectz = aspectz; }
        /**
         * @return the x-aspect ratio.
         */
        public double getAspectx() { return aspectx; }
        /**
         * @return the y-aspect ratio.
         */
        public double getAspecty() { return aspecty; }
        /**
         * @return the z-aspect ratio.
         */
        public double getAspectz() { return aspectz; }
        /** These are deprecated methods. */
        /**
         * Get the edge in the volume beyond which a voxel is not valid.
         * @deprecated
         */
        @Deprecated
        public int getEdge() { return edge; }
        /**
         * Set the edges in the volume beyond which a voxel is not valid.
         * @deprecated
         */
        @Deprecated
        public void setEdge(int edge) { this.edge = edge; }
        /**
         * Check whether x,y,z are within the edges.
         * @deprecated
         */
        @Deprecated
        public boolean in(double x, double y, double z)
        { return (x >= edge && x < width-edge && y >= edge && y < height - edge); }
        /**
         * Check whether x,y are within the edges.
         * @deprecated
         */
        @Deprecated
        public boolean valid(int x, int y)
        { return (x >= edge && x < width-edge && y >= edge && y < height - edge); }
        /**
         * Check whether x,y,z are within the edges.
         * @deprecated
         */
        @Deprecated
        public boolean valid(int x, int y, int z)
        { return (x >= edge && x < width-edge && y >= edge && y < height - edge && z >= edge && z < depth - edge); }
        /**
         * Returns the minimum depth needed to be able to filter (z-dimension) for the kernel.
        */
        public int discreteSupport(Kernel kernel)
        {
            if (kernel instanceof Kernel)
                  return Math.max(depth, kernel.support() + depth-1);
            return depth;
        }
        /**
         * Return a string describing the volume.
         */
        @Override
				public String toString()
        { return "volume: "+width+"x"+height+"x"+depth+" anisotropy: "+aspectx+"x"+aspecty+"x"+aspectz; }
}
