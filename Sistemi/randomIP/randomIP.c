#include <stdio.h>      //printf, sprintf
#include <stdlib.h>     //rand
#include "randomIP.h"
#include "generatorHelper.h"

ipAddress_t generateRandomIP(char classe, char* cidr)
{
    ipAddress_t ip;

    if(cidr == NULL)                //check if there is a cidr as input
    {
        ip.classe = classe;
        generateByClass(&ip);
    }
    else
    {
        generateByNetId(&ip, cidr);
    }
    return ip;
}

void generateByClass(ipAddress_t *ip)
{
    unsigned char octet1, octet2, octet3, octet4;
    int prefissoCidr = 0;

    switch(ip->classe)          //composing octets byte per byte
    {
    case 'A':
        octet1 = (rand() % 127) + 1;        //since the only thing that changes is the first octet in the switch case we only generate the first one, also we set the netmask
        ip->netmask = 0xFF000000;           //and the cidr prefix
        prefissoCidr = 8;
        break;

    case 'B':
        octet1 = (rand() % (191 - 128 + 1)) + 128;
        ip->netmask = 0xFFFF0000;
        prefissoCidr = 16;
        break;

    case 'C':
        octet1 = (rand() % (223 - 192 + 1)) + 192;
        ip->netmask = 0xFFFFFF00;
        prefissoCidr = 24;
        break;

    case 'D':
        octet1 = (rand() % (239 - 224 + 1)) + 224;
        ip->netmask = 0xFFFFFFFF;
        prefissoCidr = 32;
        break;

    case 'E':
        octet1 = (rand() % (255 - 240 + 1)) + 240;
        ip->netmask = 0xFFFFFFFF;
        prefissoCidr = 32;
        break;
    }

    octet2 = rand() % 256;      //we generate the rest of the octets
    octet3 = rand() % 256;
    octet4 = rand() % 256;
    ip->ip = (octet1 << 24) | (octet2 << 16) | (octet3 << 8) | octet4;          //"fusing" them into a uint32
    snprintf(ip->ipStr, sizeof(ip->ipStr), "%u.%u.%u.%u/%d", octet1, octet2, octet3, octet4, prefissoCidr);     //saving inside the ipStr a string format of the ip

}

void generateByNetId(ipAddress_t *ip, char* cidr)
{
    unsigned int prefisso = 0;
    unsigned char primoOttetto = 0;

    uint32_t netid = parseCIDR(cidr, ip);           //we call the parser that returns the net id
    uint32_t broadcast = netid | (~ip->netmask);       //we calculate the broadcast address so we can determine how many ips there are available
    uint32_t totalHost = broadcast - netid;             //by doing a subtraction


    if(totalHost>2)     //it means that other than the netid and the broadcast address there are oothes
    {
        uint32_t hostNumber = (rand() % (totalHost - 1)) + 1;     //we generate a random ip
        ip->ip = netid + hostNumber;
    }
    else
    {
        ip->ip = netid;
    }

    primoOttetto = ((ip->ip >> 24) & 0xFF);
    if(primoOttetto >= 1 && primoOttetto <= 127)
    {
        ip->classe = 'A';
    }
    else if(primoOttetto >= 128 && primoOttetto <= 191)
    {
        ip->classe = 'B';
    }
    else if(primoOttetto >= 192 && primoOttetto <= 223)
    {
        ip->classe = 'C';
    }
    else if(primoOttetto >= 224 && primoOttetto <= 239)
    {
        ip->classe = 'D';
    }
    else if(primoOttetto >= 240)
    {
        ip->classe = 'E';
    }
    else
    {
        ip->classe = '?';
    }

    sscanf(cidr, "%*u.%*u.%*u.%*u/%u", &prefisso);

    snprintf(ip->ipStr, sizeof(ip->ipStr), "%u.%u.%u.%u/%d", primoOttetto, ((ip->ip >> 16) & 0xFF), ((ip->ip >> 8) & 0xFF), (ip->ip & 0xFF), prefisso);
}
