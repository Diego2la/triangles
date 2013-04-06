/*
   Project: TriangleManager
   Functional area: Program working with binary files containing triangles
   Author: Tyurin Dima
   Contact data: tyurin94@gmail.com
   Creation: 16.03.2013
*/
   
// external libs
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>

#define TRUE 1
#define FALSE 0

// config global variables
const int g_fileNameSize = 30;

struct Triangle
{
   int number;
   double x1;
   double y1;
   double x2;
   double y2;
   double x3;
   double y3;
};
   
struct AgrData
{
   int    argc;
   char** argv;
};

// receiving value from strings
int checkFileName  (const char* file);
int getDouble      (const char* str, double* d);
int getInt         (const char* str, int* i);

// program commands
int command_add    ( struct AgrData* data );
int command_del    ( struct AgrData* data );
int command_showall( struct AgrData* data );

int main(int _argc, char* _argv[])
{
   struct AgrData data;
   data.argc = _argc;
   data.argv = _argv;

   int retCode = 0;

   if (_argc > 1)
   {
      if ( strcmp(_argv[1], "add") == 0 )
         retCode = command_add(&data);
      else if ( strcmp(_argv[1], "del") == 0)
         retCode = command_del(&data);
      else if ( strcmp(_argv[1], "showall") == 0)
         retCode = command_showall(&data);
      else retCode = 3;
   }

   if (retCode)
      printf("Error : %d\n", retCode);
   return retCode;
}

int checkFileName(const char* file)
{
   assert(file);
   if (strlen(file) < g_fileNameSize)
      return TRUE;
   return FALSE;
}
int getDouble(const char* str, double* d)
{
   assert(str);
   *d = atof(str);
   return TRUE;
}
int getInt(const char* str, int* i)
{
   assert(str);
   *i = atoi(str);
   return TRUE;
}

int command_add( struct AgrData* data )
{
   assert(data);

   const char* fileName = data->argv[2];
   struct Triangle t;

   // receiving file and Triangle
   if (data->argc != 10)                   return 3;
   if (!checkFileName(fileName))           return 3;
   if (!getInt(data->argv[3], &t.number))  return 3;
   if (!getDouble(data->argv[4], &t.x1))   return 3;
   if (!getDouble(data->argv[5], &t.y1))   return 3;
   if (!getDouble(data->argv[6], &t.x2))   return 3;
   if (!getDouble(data->argv[7], &t.y2))   return 3;
   if (!getDouble(data->argv[8], &t.x3))   return 3;
   if (!getDouble(data->argv[9], &t.y3))   return 3;

   FILE* file = fopen(fileName,"ab");
   if (!file) return 2;

   fwrite(&t, sizeof(t), 1, file);
   fclose(file);

   return 0;
}
int command_del( struct AgrData* data )
{
   assert(data);

   return 0;
}
int command_showall( struct AgrData* data )
{
   assert(data);

   const char* fileName = data->argv[2];
   struct Triangle t;

   // receiving file and name
   if (data->argc != 3)              return 3;
   if (!checkFileName(fileName))     return 3;

   FILE* file = fopen(fileName,"rb");
   if (!file) return 1;


   // obtain file size
   fseek(file , 0 , SEEK_END);
   int fSize = ftell(file) / sizeof(t);
   rewind (file);

   int i;

   printf("------------------------------------------------------------------------\n");
   printf("Number of Triangles in file \"%s\" = %d\n", fileName, fSize);
   printf("Sizeof(Triangle) = %d bit(s)\n", sizeof(t));

   printf("------------------------------------------------------------------------\n");
   for (i = 0; i < fSize; ++i)
   {
      fread(&t, sizeof(t), 1, file);
      printf("%d) Triangle N %d = { (%0.2f, %0.2f), (%0.2f, %0.2f), (%0.2f, %0.2f) }\n",
            i + 1, t.number, t.x1, t.y1, t.x2, t.y2, t.x3, t.y3);
   }
   printf("------------------------------------------------------------------------\n");

   fclose(file);
   return 0;
}
