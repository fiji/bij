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
import volume.Gaussian;
import volume.Volume;
import volume.VolumeShort;

/**
 * This class implements utility for surface plotting.
 * Surface plotting is a rendering technique that displays a 2-D image in the form
 * of a colored, shaded volume rendering.
 * The 'heights' of the shape in the volume rendering
 * derive from the pixel values in the 2-D image.
 *
 * Algorithms, implementation (c) Michael Abramoff
 * Idea from:
 * Paulo Magalhaes, PhD
 * University of Padua
 * Dept. Biomedical Sciences
 * Viale G. Colombo, 3             Tel: +39 049 827 6065
 * I-35121 Padua - Italy           Fax: +39 049 827 6049
 *
 * Copyright (c) 1999-2003, Michael Abramoff. All rights reserved.
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
public class VJSurfacePlotShell extends VJRenderViewCine
{
        /**
         * The image currently being rendered.
         */
        private int     k;
        /**
         * The Volume containing the images to be rendered.
         */
        private Volume vimages;
        private float min, max, aspectz;
        private float sigma;

        /**
         * Instantiates a new surface plotting rendering shell.
         * This shell can render a stack of surface plots from a stack of images.
         * @param renderer a VJRenderer
         * @param scale the amount by which to scale the volume
         * @param xrot the amount by which to rotate in X
         * @param yrot the amount by which to rotate in Y
         * @param zrot the amount by which to rotate in Z
         * @param vimages a VolumeShort containing the images to be surface plotted.
         * @param min the minimum value within vimages (determines the height of each surface volume)
         * @param max the maximum value within vimages (determines the height of each surface volume)
         * @param aspectz the aspect ration in the z-direction.
         * @param sigma Standard deviation of a Gaussian smoothing kernel before rendering starts.
         * @param message a useful message to identify the characteristics of this rendering.
         */
        public VJSurfacePlotShell(VJRenderer renderer,
                float scale, float xrot, float yrot, float zrot,
                Volume vimages, float min, float max, float aspectz, float sigma, String message)
        {
                super(renderer, scale, xrot, yrot, zrot, message, 0, false);
                // Calculate number of images.
                this.vimages = vimages;
                this.n = vimages.getDepth();
                this.k = 0;
                this.max = max;
                this.min = min;
                this.aspectz = aspectz;
                this.sigma = sigma;
                // Set the first volume.
                // Creates a surface volume out of image slice 0 in vimages
                VolumeShort v = imagesVolumeToSurfaceVolume((VolumeShort) vimages, 0, max, min, aspectz, sigma);
                // Reset the volume in the renderer
                renderer.setVolume(v);
        }
        /**
        * Prepare for the (k+1) th rendering in a cine rendering.
        * In this case, get the k+1 image from vimages and make a volume from it to be rendered.
        * @param k the number of the current (i.e. before the next) rendering.
        */
        protected void nextView(int k)
        {
                // Do the k'th in the vimages volume.
                // Creates a surface volume out of image slice k in vimages
                if ((k+1) < n)
                {
                        VolumeShort v = imagesVolumeToSurfaceVolume((VolumeShort) vimages, k + 1, max, min, aspectz, sigma);
                        // Reset the volume in the renderer
                        renderer.setVolume(v);
                        renderer.setMessage(""+(k+1)+"/"+n);
                }
        }
        /**
         * Convert the k'th 2-D image slice in vimages to a surface VolumeShort.
         * This means that it contains an index increasing with increasing y,
         * and that intensities in the 2-D image are converted into columns of corresponding height in the sufrace volume.
         * The image in vimages is extruded along the y-axes of the surface volume.
         * The columns start at y=0 and extend up till the pixel intensity of the
         * corresponding pixel in the 2-D image, and are filled with voxel value 255.
         * All others are filled with 0.
         * The index value of every voxel is set to the y value of that voxel.
         * For now, only 8-bit and 16-bit images can be processed.
         * @param vimages an VolumeShort containing the 2-D images.
         * @param k the index to vimages.
         * @param max the eventual number of slices in the volume
         * @param min the minimum pixel value in vimages.v[k].
         * @param aspectz
         * @param sigma
         */
        protected static VolumeShort imagesVolumeToSurfaceVolume(VolumeShort vimages,
                int k, float max, float min, float aspectz, float sigma)
        {
                int range = (int) (max - min);
                // Create a new volume with the surface.
                VolumeShort v = new VolumeShort(vimages.getWidth(),
                        vimages.getHeight(), range+2, 1, 1, aspectz);
                v.setIndexed(true);
                // From 0 to height of volume.
                for (int z = 0; z < v.getDepth(); z++)
                {
                        // z=0 is the top of the volume.
                        // zinv is the z counting from the bottom of the volume.
                        int zinv = v.getDepth() - z - 1;
                        float threshold = zinv + min;
                        for (int y = 0; y < v.getHeight(); y++)
                        for (int x = 0; x < v.getWidth(); x++)
                        {
                                float intensity = vimages.v[k][y][x];
                                // Is the column at x,height-y up to threshold?
                                if (intensity > threshold)
                                        v.v[z][y][x] = (short) 255;
                                else
                                        v.v[z][y][x] = (short) 0;
                        }
                }
                // Smooth with Gaussian kernel.
                if (sigma > 0.0)
                {
                        VJUserInterface.status("Smoothing ("+sigma+")...");
                        // Smooth only the voxel values, not the index values.
                        v.convolvexyz(new Gaussian(sigma));
                }
                // Set the proper index values.
                for (int z = 0; z < v.getDepth(); z++)
                {
                        // Set all voxel indices of slice z to the inverse of z, the index into a 255 entry color LUT.
                        setSliceIndex(v, z, v.getDepth() - z - 1);
                }
                return v;
        }
        /**
         * Set the index values in VolumeShort v for voxels at slice y to index.
         * @param v the volume
         * @param z the slice in the volume (on z-axis) [0-v.getDepth()].
         * @param index the index value to set all voxels in slice z to [0-255]
         */
        private static void setSliceIndex(VolumeShort v, int z, int index)
        {
                index = Math.min(index, 255);
                for (int y = 0; y < v.getHeight(); y++)
                for (int x = 0; x < v.getWidth(); x++)
                        v.v[z][y][x] = (short) (((index<<8)&0x0000ff00) | (v.v[z][y][x] & 0x000000ff));
        }
}

