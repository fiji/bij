# Biomedical Imaging in Java

This is Fiji's fork of Michael Abramoff's "Biomedical Imaging in Java" (bij)
library. The original project website is here:

    http://bij.isi.uu.nl/

Unfortunately, the bij source distribution does not include all of the bij
classes, so the Fiji team decompiled the missing ones, resulting in this fork:
a fully functional source distribution which you can build and change in true
open source spirit.

Unfortunately, bij's license (as seen in source headers) is not open source:

    Copyright (c) 1999-2004, Michael Abramoff. All rights reserved.

    This source code, and any derived programs ('the software')
    are the intellectual property of Michael Abramoff.
    Michael Abramoff asserts his right as the sole owner of the rights
    to this software.

    Commercial licensing of the software is available by contacting the author.
    THE SOFTWARE IS PROVIDED "AS IS" AND WITHOUT WARRANTY OF ANY KIND,
    EXPRESS, IMPLIED OR OTHERWISE, INCLUDING WITHOUT LIMITATION, ANY
    WARRANTY OF MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.

This component contains the bij library code only, without the FlowJ, VolumeJ,
SurfaceJ or Volume Reconstructor plugins. For those plugins, which are built on
this library, see:

    https://github.com/fiji/FlowJ
