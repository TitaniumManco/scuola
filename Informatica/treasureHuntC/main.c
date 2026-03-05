#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define N 5

void riempiMatrice(char matrix[N][N])
{
    int i=0, j=0;


    for(i=0; i<N; i++)
    {
        for(j=0; j<N; j++)
        {
            matrix[i][j]=' ';
        }
    }

}

void stampaMatrice(char matrix[N][N])
{
    int i=0, j=0;


    for(i=0; i<N; i++)
    {
        printf("\n");
        printf("\t");

        for(j=0; j<N; j++)
        {

            printf("%c\t", matrix[i][j]);
            if(j<N-1)
            {
                printf("|\t");
            }
        }

        printf("\n");
        if(i<N-1)
        {
            printf("----------------|---------------|---------------|---------------|----------------");
        }
    }

}

int controlloEsistenza(int i)
{
    while(i<0 || i>N)
    {
        printf("Posizione inesistente, inserire un valore tra 0 e 4: ");
        scanf("%d", &i);
    }
    return i;
}

int inputx(int i)
{
    printf("\nProva a indovinare dove si trova il tesoro, inserisci la colonna: ");
    scanf("%d", &i);
    i = controlloEsistenza(i);

    return i;
}

int inputy(int i)
{
    printf("Inserisci la riga: ");
    scanf("%d", &i);
    i = controlloEsistenza(i);

    return i;
}

int controlloWin(int userAltezza, int userLunghezza, int tesoroAltezza, int tesoroLunghezza)
{
    int distanza=0, placeholder=0;

    if(userAltezza>userLunghezza)
    {
        placeholder=userAltezza;
        userAltezza=userLunghezza;
        userLunghezza=placeholder;
    }

    if(tesoroAltezza>tesoroLunghezza)
    {
        placeholder=tesoroAltezza;
        tesoroAltezza=tesoroLunghezza;
        tesoroLunghezza=placeholder;
    }

    if(userLunghezza>tesoroLunghezza)
    {
        placeholder=userLunghezza;
        userLunghezza=tesoroLunghezza;
        tesoroLunghezza=placeholder;
    }

    distanza=tesoroLunghezza-userLunghezza;

    if(distanza==0)
    {
        return 1;
    }
    else if(distanza==1)
    {
        return 2;
    }
    else if(distanza==2)
    {
        return 3;
    }

    return 4;
}

int finale(int esito, int turni)
{
    if(esito==1)
    {
        printf("Hai vinto! Ci hai messo %d turni", turni);
        return 1;
    }
    else if(esito==2)
    {
        printf("Molto vicino...");
        return 0;
    }
    else if(esito==3)
    {
        printf("Vicino");
        return 0;
    }

    printf("Lontano");
    return 0;
}

void stampaX(char matrix[N][N], int i, int j)
{
    matrix[j][i]='X';
    stampaMatrice(matrix);
}

int main()
{
    char matrice[N][N];
    int treasurex=0, treasurey=0, gw=0, counter=0, userx=0, usery=0, result=0;
    srand(time(NULL));

    riempiMatrice(matrice);

    printf("Il campo da gioco e' il seguente\n");
    treasurex=(rand() % 5) + 0;
    treasurey=(rand() % 5) + 0;
    matrice[treasurey][treasurex]= 'T';

    stampaMatrice(matrice);



    while(gw!=1)
    {
        userx=inputx(userx);
        usery=inputy(usery);
        stampaX(matrice, userx, usery);
        result=controlloWin(usery, userx, treasurey, treasurex);
        counter++;
        gw=finale(result, counter);
    }
    return 0;
}
