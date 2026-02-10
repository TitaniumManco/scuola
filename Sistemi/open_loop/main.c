#include <stdio.h>
#include <stdlib.h>
#include <io.h>

typedef struct FileEntry {

    FILE* fp;
    unsigned int indice;
    intptr_t kernelHandle;

} FileEntry_t;

typedef struct OpenInfo {

    unsigned int aperture_riuscite;
    unsigned int aperture_fallite;
    FileEntry_t *listaPrecedente;
    unsigned int dimensione_precedente;

} OpenInfo_t;



int main(int argc, char *argv[])
{

    if(argc<2)
    {
        printf("Per usare %s inserire come parametro il file che si vuole aprire", argv[0]);
        return 1;
    }

    unsigned int dim = 10;          //we use a variable for the size so we can increase it in an easier way
    unsigned int aperture = 0;      //counter for opened files
    unsigned int errori = 0;        //counter for errors

    FileEntry_t *arr = malloc(dim * sizeof(FileEntry_t));       //dynamic allocation of an array of type FileEntry_t whose size is dim

    if(arr == NULL)
    {
        perror("malloc");
        return 1;
    }

    while(1)
    {
        FILE* fp = fopen(argv[1], "r");

        if(fp == NULL)
        {
            perror("fopen");
            errori++;
            break;
        }

        int fd = _fileno(fp);           // file descriptor C
        intptr_t h = _get_osfhandle(fd);        // native Windows handle

        arr[aperture].fp = fp;              //saving the informations about the opened file
        arr[aperture].indice = aperture;    //
        arr[aperture].kernelHandle = h;     //
        aperture++;

        if(aperture >= dim) //if we open more files than what we can contain we need to get a bigger array
        {
            dim = dim*2;        //we just double the size and use realloc
            FileEntry_t *newArr = realloc(arr, dim * sizeof(FileEntry_t));

            if(newArr == NULL)
            {
                perror("realloc");
                break;
            }

            arr = newArr;
        }
    }

    for(size_t i = 0; i < aperture; i++)
    {
        fclose(arr[i].fp);      //closing files
    }

    OpenInfo_t stat;
    stat.aperture_fallite = errori;
    stat.aperture_riuscite = aperture;
    stat.dimensione_precedente = 0;     // i didn't understand
    stat.listaPrecedente = NULL;        // what to do with it

    printf("\n###### REPORT FINALE ######\n");
    printf("File testato: %s\n", argv[1]);
    printf("Aperture riuscite: %u\n", stat.aperture_riuscite);
    printf("Aperture fallite: %u\n", stat.aperture_fallite);
    printf("Capacita' finale array: %u\n", dim);
    printf("Tutti i file sono stati chiusi.\n");

    free(arr);
    return 0;
}
