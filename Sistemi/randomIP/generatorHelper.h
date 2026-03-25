#ifndef GENERATORHELPER_H_INCLUDED
#define GENERATORHELPER_H_INCLUDED
#include <stdint.h>


typedef struct {
    char ipStr[19];           // es. "192.168.010.001/24")
    char classe;            // es. (A, B, C, D, E)
    uint32_t ip;
    uint32_t netmask;
} ipAddress_t;

void ipPrinter(ipAddress_t *ip);

uint32_t calculatePrefix(int cidrPrefix);

uint32_t parseCIDR(char *ipStr, ipAddress_t *ip);

#endif // GENERATORHELPER_H_INCLUDED
