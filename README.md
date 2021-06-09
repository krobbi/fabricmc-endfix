# Endfix
__v2.0.0__ for Minecraft Fabric __1.17.x__  
__MIT License__ - https://krobbi.github.io/license/2021/mit.txt  
__Copyright &copy; 2020 Chris Roberts__ (Krobbizoid)

# Contents
1. [About](#about)
2. [Summary](#summary)
3. [Download](#download)
4. [Credits](#credits)
5. [License](#license)

# About
Endfix is a Minecraft Fabric mod that fixes a bug where End spikes and gateways
generate asymmetrically since version 1.14
([MC-151399](https://bugs.mojang.com/browse/MC-151399)).

The mod also makes End portals visible from the side for visual enhancement in
creative mode building, or technical usages involving the removal of End portal
frames.

v2.0.0 is confirmed working in `Minecraft 1.17`
with `Fabric Loader 0.11.3-1.17`
and `Fabric API 0.34.9+1.17`.

The mod will only have an effect when End spikes and gateways are generated. It
will not fix existing end gateways, but it will move End spikes that are
regenerated by respawning the ender dragon. Note that if the End spikes move,
misplaced obsidian will be left behind.

It is recommended to keep the mod installed as the positions of the End spikes
are cached. This cache is not saved to disk and is lost when the server (or
single-player internal server) restarts, or if the world seed changes.

Minecraft 1.17, and version 2.0.0 of Endfix require Java 16.

NOT AN OFFICIAL MINECRAFT PRODUCT. NOT APPROVED BY OR ASSOCIATED WITH MOJANG.

# Summary
* Endfix fixes some minor, mostly visual issues with several End-related
structures.
* You are free to use the mod in mod packs without permission.
* There are currently no plans to make a Forge version of the mod.
* The mod will not be developed for snapshot versions of Minecraft.
* The Fabric mod ID for Endfix is `krobbi_endfix`.

# Download
The mod can be downloaded from the
[releases page](https://github.com/krobbi/fabricmc-endfix/releases).

# Credits
Logo color palette: [Faraway48](https://lospec.com/palette-list/faraway48) by
[Igor Ferreira](https://twitter.com/diemorth).

_None of the currently listed credits are affiliated with Endfix._

# License
MIT License - https://krobbi.github.io/license/2021/mit.txt

---
MIT License

Copyright (c) 2021 Chris Roberts

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
