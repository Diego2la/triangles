echo "Testing TriangleManager"
del test.bin
TriangleManager add test.bin 1 2 4 8 16 32 64
TriangleManager add test.bin 2 0 1 2 0 0 2
TriangleManager add test.bin 7 0 0 0 4 2 0
TriangleManager add test.bin 7 0 0 0 4 4 0
rem TriangleManager add test.bin 4 9 1 2 3 4 5
rem TriangleManager add test.bin 7 0 1 2 3 4 5
rem TriangleManager add test.bin 7 0 1 1 2 3 4
TriangleManager showall test.bin
