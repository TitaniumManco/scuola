#include <stdio.h>
#include <stdlib.h>
#define N 3

void riempiMatrice(char matrix[N][N])
{
    int i=0, j=0;
    for(i=0; i<N; i++)
    {
        for(j=0; j<N; j++)
        {
            matrix[i][j] = ' ';
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
            printf("----------------|---------------|----------------");
        }
    }
}

void inputX(char trisx[N][N])
{
    int a=0, b=0;
    printf("\nGiocatore delle X sceglie le coordinate partendo dalle righe (da 0 a 2): ");
    scanf("%d", &a);

    while(a>2 || a<0)
    {
        printf("Indice non valido, scegliere una posizione da 0 a 2: ");
        scanf("%d", &a);
    }

    printf("Adesso scegliere la colonna (da 0 a 2): ");
    scanf("%d", &b);

    while(b>2 || b<0)
    {
        printf("Indice non valido, scegliere una posizione da 0 a 2: ");
        scanf("%d", &b);
    }

    while(trisx[a][b] != ' ')
    {
        printf("Posizione già occupata, reinserire gli indici in ordine riga, colonna\n");
        scanf("%d", &a);
        scanf("%d", &b);
    }

    trisx[a][b] = 'X';
}

void inputO(char triso[N][N])
{
    int a=0, b=0;
    printf("\nGiocatore delle O sceglie le coordinate partendo dalle righe (da 0 a 2): ");
    scanf("%d", &a);

    while(a>2 || a<0)
    {
        printf("Indice non valido, scegliere una posizione da 0 a 2: ");
        scanf("%d", &a);
    }

    printf("Adesso scegliere la colonna (da 0 a 2): ");
    scanf("%d", &b);

    while(b>2 || b<0)
    {
        printf("Indice non valido, scegliere una posizione da 0 a 2: ");
        scanf("%d", &b);
    }

    while(triso[a][b] != ' ')
    {
        printf("Posizione già occupata o inesistente, reinserire gli indici in ordine riga colonna\n");
        scanf("%d", &a);
        scanf("%d", &b);
    }

    triso[a][b] = 'O';
}

int controlloWin(char winCon[N][N])
{
    int i, j;

    for(i = 0; i < 3; i++)
    {
        if(winCon[i][0] != ' ' && winCon[i][0] == winCon[i][1] && winCon[i][1] == winCon[i][2])
        {
            if(winCon[i][0] == 'X')
                return 1;
            else
                return 2;
        }
    }

    for(j = 0; j < 3; j++)
    {
        if(winCon[0][j] != ' ' && winCon[0][j] == winCon[1][j] && winCon[1][j] == winCon[2][j])
        {
            if(winCon[0][j] == 'X')
                return 1;
            else
                return 2;
        }
    }

    if(winCon[0][0] != ' ' && winCon[0][0] == winCon[1][1] && winCon[1][1] == winCon[2][2])
    {
        if(winCon[0][0] == 'X')
            return 1;
        else
            return 2;
    }

    if(winCon[0][2] != ' ' && winCon[0][2] == winCon[1][1] && winCon[1][1] == winCon[2][0])
    {
        if(winCon[0][2] == 'X')
            return 1;
        else
            return 2;
    }

    return 0;
}

int main()
{
    char matrice[N][N], output = 0;
    int i = 0;

    printf("La tabella e' la seguente\n");

    riempiMatrice(matrice);
    stampaMatrice(matrice);

    while(i < 9 && (output != 1 && output != 2))
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
        printf("Ha vinto il giocatore delle X!\n");
    else if(output == 2)
        printf("Ha vinto il giocatore delle O!\n");
    else
        printf("Partita finita in pareggio.\n");

    return 0;
}