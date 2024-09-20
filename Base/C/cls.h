#ifndef CLS_H
#define CLS_H

#include <stdlib.h>

void cls(){
#if defined(_WIN32) || defined(_WIN64) || defined(__WINDOWS__)
    system("cls");
#elif defined(__linux__)
    system("clear");
#elif defined(__APPLE__) && defined(__MACH__)
    system("clear");
#elif defined(unix) || defined(__unix__) || defined(__unix)
    system("clear");
#else
    #error Unknown_OS
#endif
}

#endif
