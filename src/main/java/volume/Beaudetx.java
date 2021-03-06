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
 *  This class implements Beaudet differentiation in x direction.
 *
 * (c) 1999-2002 Michael Abramoff. All rights reserved.
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
public class Beaudetx extends Kernel2D
{
    /** Initialize Beaudet kernel. */
    public Beaudetx()
    {
            int width = 5;
	          if (width % 2 == 0) width++;
            halfwidth = width / 2;
            k = new double[5][5];
            k[0][0] = -2 / 50; k[1][0] = -1 / 50; k[2][0] = 0 / 50;
            k[3][0] = 1  / 50;  k[4][0] = 2 / 50;
            k[0][1] = -2 / 50; k[1][1] = -1 / 50; k[2][1] = 0 / 50;
            k[3][1] = 1  / 50;  k[4][1] = 2 / 50;
            k[0][2] = -2 / 50; k[1][2] = -1 / 50; k[2][2] = 0 / 50;
            k[3][2] = 1  / 50;  k[4][2] = 2 / 50;
            k[0][3] = -2 / 50; k[1][3] = -1 / 50; k[2][3] = 0 / 50;
            k[3][3] = 1  / 50;  k[4][3] = 2 / 50;
            k[0][4] = -2 / 50; k[1][4] = -1 / 50; k[2][4] = 0 / 50;
            k[3][4] = 1  / 50;  k[4][4] = 2 / 50;
    }
}
