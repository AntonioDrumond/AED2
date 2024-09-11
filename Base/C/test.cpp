#include <iostream>
#include "wait.h"

int main(){
	for(int i=0; i<10; i++){
		std::cout << i << "\n";
		wait(0.5);
	}
	return 0;
}
