#include <stdio.h>      //printf, sscanf
#include "generatorHelper.h"


void ipPrinter(ipAddress_t *ip)
{
    unsigned char netmask[4];

    netmask[0] = (ip->netmask >> 24) & 0xFF;
    netmask[1] = (ip->netmask >> 16) & 0xFF;
    netmask[2] = (ip->netmask >> 8) & 0xFF;
    netmask[3] = ip->netmask & 0xFF;

    printf("\nIndirizzo IP: %s\n", ip->ipStr);
    printf("Netmask: %u.%u.%u.%u\n", netmask[0], netmask[1], netmask[2], netmask[3]);
    printf("Classe: %c\n", ip->classe);
}

uint32_t calculatePrefix(int cidrPrefix)
{
    if(cidrPrefix == 0)
    {
        return 0;
    }

    if(cidrPrefix == 32)
    {
        return 0xFFFFFFFF;
    }

    uint32_t uni = 0xFFFFFFFF;

    return uni << (32 - cidrPrefix);
}

uint32_t parseCIDR(char *ipStr, ipAddress_t *ip)
{
    unsigned int octet[4];
    unsigned int prefisso;

    sscanf(ipStr, "%u.%u.%u.%u/%u", &octet[0], &octet[1], &octet[2], &octet[3], &prefisso);

    ip->netmask = calculatePrefix(prefisso);

    return ((octet[0] << 24) | (octet[1] << 16) | (octet[2] << 8) | octet[3]) & ip->netmask;
}
