#ifndef WAIT_H
#define WAIT_H

#include <stdlib.h>

#if defined(_WIN32) || defined(_WIN64) || defined(__WINDOWS__)
	#include <windows.h>
	#define slep Sleep
	#define conSecondsWait 1000
#elif defined(__linux__)
	#include <unistd.h>
	#define slep usleep
	#define conSecondsWait 1000000
#else
	#error Unknown_OS
#endif

void wait(double x){
	double time = x * conSecondsWait;
	slep(time);
}

#endif
