#include <stdio.h>
#include <stdlib.h>
#define N 6
#define L 7

void riempiMatrice(char matrix[N][L])
{
    int i=0, j=0;
    for(i=0; i<N; i++)
    {
        for(j=0; j<L; j++)
        {
            matrix[i][j] = ' ';
        }
    }
}

void stampaMatrice(char matrix[N][L])
{
    int i=0, j=0;
    for(i=0; i<N; i++)
    {
        printf("\n");
        printf("\t");

        for(j=0; j<L; j++)
        {
            printf("%c\t", matrix[i][j]);
            if(j<L-1)
            {
                printf("|\t");
            }
        }

        printf("\n");
        if(i<N)
        {
            printf("----------------|---------------|---------------|---------------|---------------|---------------|---------------");
        }
    }
}

void inputX(char trisx[N][L])
{
    int a=0, i=0;
    printf("\nGiocatore delle X sceglie la colonna (da 0 a 6): ");
    scanf("%d", &a);

    while(a>6 || a<0)
    {
        printf("Indice non valido, scegliere una posizione da 0 a 6: ");
        scanf("%d", &a);
    }

    i=N-1;

    while(i>=0 && trisx[i][a]!= ' ')
    {
        i--;
    }

    if(i>=0)
    {
        trisx[i][a] = 'X';
    }
    else
    {
        printf("Colonna piena!\n");
    }
}

void inputO(char triso[N][L])
{
    int a=0, i=0;
    printf("\nGiocatore delle O sceglie la colonna (da 0 a 6): ");
    scanf("%d", &a);

    while(a>6 || a<0)
    {
        printf("Indice non valido, scegliere una posizione da 0 a 6: ");
        scanf("%d", &a);
    }

    i=N-1;


    while(i>=0 && triso[i][a]!= ' ')
    {
        i--;
    }

    if(i>=0)
    {
        triso[i][a] = 'O';
    }
    else
    {
        printf("Colonna piena!\n");
    }
}

int controlloWin(char winCon[N][L])
{
    int i, j;
//verticale
    for(i = 0; i < N - 3; i++)
    {
        for(j = 0; j < L; j++)
        {
            if(winCon[i][j] != ' ' && winCon[i][j] == winCon[i+1][j] && winCon[i+1][j] == winCon[i+2][j] && winCon[i+2][j] == winCon[i+3][j])
            {
                if(winCon[i][0] == 'X')
                {
                    return 1;
                }
                else
                {
                    return 2;
                }
            }
        }
    }
//orizzontale
    for(i = 0; i < N; i++)
    {
        for(j = 0; j < L - 3; j++)
        {
            if(winCon[i][j] != ' ' && winCon[i][j] == winCon[i][j+1] && winCon[i][j+1] == winCon[i][j+2] && winCon[i][j+2] == winCon[i][j+3])
            {
                if(winCon[i][0] == 'X')
                {
                    return 1;
                }
                else
                {
                    return 2;
                }
            }
        }
    }
//diagonale "principale"
    for(i = 0; i < N - 3; i++)
    {
        for(j = 0; j < L - 3; j++)
        {
            if(winCon[i][j] != ' ' && winCon[i][j] == winCon[i+1][j+1] && winCon[i+1][j+1] == winCon[i+2][j+2] && winCon[i+2][j+2] == winCon[i+3][j+3])
            {
                if(winCon[i][0] == 'X')
                {
                    return 1;
                }
                else
                {
                    return 2;
                }
            }
        }
    }
//diagonale secondaria?

    return 0;
}

int main()
{
    char matrice[N][L], output = 0;
    int i = 0;

    printf("La tabella e' la seguente\n");

    riempiMatrice(matrice);
    stampaMatrice(matrice);

    while(i < 42 && (output != 1 && output != 2))
    {
        inputX(matrice);
        i++;
        stampaMatrice(matrice);
        if(controlloWin(matrice) == 1)
        {
            output = 1;
        }

        if(output != 1)
        {
            inputO(matrice);
            i++;
            stampaMatrice(matrice);
            if(controlloWin(matrice) == 2)
            {
                output = 2;
            }
        }
    }

    if(output == 1)
        printf("\nHa vinto il giocatore delle X!\n");
    else if(output == 2)
        printf("\nHa vinto il giocatore delle O!\n");
    else
        printf("\nPartita finita in pareggio.\n");

    return 0;
}
