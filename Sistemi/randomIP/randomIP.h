#ifndef RANDOMIP_H_INCLUDED
#define RANDOMIP_H_INCLUDED
#include "generatorHelper.h"

ipAddress_t generateRandomIP(char classe, char* cidr);

void generateByClass(ipAddress_t *ip);

void generateByNetId(ipAddress_t *ip, char* cidr);


#endif // RANDOMIP_H_INCLUDED
