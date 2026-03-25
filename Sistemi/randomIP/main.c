#include <stdio.h>      //printf
#include <stdlib.h>     //atoi
#include <time.h>
#include "generatorHelper.h"
#include "randomIP.h"

int main(int argc, char *argv[])
{
    int ipCount = 1;
    char ipClass = 'C';
    char* CIDR=NULL;

    srand(time(NULL));

    if(argc>1)
    {
        for(int i = 1; i < argc; i++)
        {
            if(argv[i][0] == '-' && argv[i][1] == 'h')
            {
                printf("Uso di %s: [filename][-n][-c][-i]\n   -n   specifica il numero di ip casuali da generare\n   -c  specifica la classe degli ip da generare\n   -i  ipecifica la NetID con la notazione CIDR (ad es. 123.200.76.0/23)", argv[0]);
                return 0;
            }
            else if(argv[i][0] == '-' && argv[i][1] == 'n')
            {
                ipCount = atoi(&argv[i][2]);
            }
            else if(argv[i][0] == '-' && argv[i][1] == 'c')
            {
                ipClass = argv[i][2];
            }
            else if(argv[i][0] == '-' && argv[i][1] == 'i')
            {
                CIDR = &argv[i][2];
            }
            else
            {
                printf("Opzione non riconosciuta, usa '-h' per l'aiuto....");
                return 1;
            }
        }
    }
    else
    {
        ipCount = 1;
    }

    for(int i=0; i<ipCount; i++)
    {
        ipAddress_t ip = generateRandomIP(ipClass, CIDR);
        ipPrinter(&ip);
    }

    return 0;
}
