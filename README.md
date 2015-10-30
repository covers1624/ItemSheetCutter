ItemSheetCutter
===============

This is a java program that can be used to cut up minecraft texture sheets for pre 1.5 versions of the game.

Args:
```
	-in <FileName> : Specifies the input file. it will only grab input files from the current directory.
	-res <TexPackSize> : For example, normal minecraft assets are in 16x16 format, you may have a texture pack for 128x128, Specify that here or it will default to 16.
All cut textures will be placed in /out/<InFileName>/
```


An example of how to use it would be:
	java -jar ItemSheetCutter.jar -in <file.png> -res 16
	
ALL RIGHTS RESERVED

﻿
Code Permissions:

You CAN
-Fork and modify the code.
-Submit Pull Requests to this repository.
-Copy portions of this code for use in other projects.
-Write your own code that uses this code as a dependency.
-Fork this project for the purpose of creating a pull request.

You CANNOT
-Redistribute this in its entirety as source or compiled code.
-Create or distribute code which contains 50% or more Functionally Equivalent Statements* from this repository.


This is based on the CoFH "Don't Be a Jerk" License
https://github.com/CoFH/CoFHCore/blob/master/README.md
