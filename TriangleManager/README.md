TriangleManager
===============
This is a part of lab2 (4 sem) wich create/show triangles from binary files. 
Program works only with console arguments.

Types of arguments
------------------
+ String   filename
+ int      number 
+ double   x1
+ double   y1
+ double   x2
+ double   y2
+ double   x3
+ double   y3

Commands
--------
- Add new triangle to file

    `add <filename> <number> <x1> <y1> <x2> <y2> <x3> <y3>`

- Delete all equal triangles from file ( not supported now )

    `del <filename> <number>`

- Show all triangles from file

    `showall <filename>`
	
Return codes
--------
0. Success
1. File not found
2. Cannot write to file
3. Invalid args
   
