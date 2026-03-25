import random
import os

cards = [11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10]

repeat='s'

while repeat=='s':
    userHand = []
    cpuHand = []
    userWin=0
    cpuWin=0
    draw=''
    busted=False
    game = True
    os.system('cls')
    for i in range(2):
        x=random.choice(cards)
        if x==11:
            if sum(userHand)+11>21:
                x=1
        userHand.append(x)
        
        x=random.choice(cards)
        if x==11:
            if sum(cpuHand)+11>21:
                x=1
        cpuHand.append(x)
        
    print(userHand, cpuHand[0])
        
    if sum(cpuHand)==21:
        cpuWin=1
        game=False
    elif sum(userHand)==21:
        userWin=1
        game=False
            
    if cpuWin == 1:
        print("VINCE LA CPU")
    if userWin == 1:
        print("VINCE IL GIOCATORE")

    while game and sum(userHand)<=21:
        draw=input("Vuoi pescare una carta? s/n: ")
        
        while draw!='s' and draw!='n':
            draw=input("Input non valido, scegliere un valore tra s/n: ")
        
        if draw=='s':
            x = random.choice(cards)
            if x==11:
                if sum(userHand)+11>21:
                    x=1
            print(f'Carta pescata: {x}')
            userHand.append(x)
            print(f'Il nuovo mazzo è: {userHand}')
            
        if draw=='n':
            game=False

    if sum(userHand)>21:
        busted=True
        print(f"Busted! Il tuo totale ({sum(userHand)}) supera il valore 21")
        print("VINCE LA CPU")
    else:
        while sum(cpuHand)<16:
            cpuHand.append(random.choice(cards))

    if userWin!=1 and cpuWin!=1 and busted==False:  
        if sum(cpuHand)>21:
            print(f"Busted! Il totale della cpu (sum{cpuHand}) supera il valore 21")
            print("VINCE IL GIOCATORE")
            
        elif sum(cpuHand)>sum(userHand):
            print('VINCE LA CPU')

        elif sum(cpuHand)==sum(userHand):
            print('PAREGGIO')
               
        else:
            print("VINCE IL GIOCATORE")

    print(f"Mazzo del giocatore {userHand}")
    print(f"Punteggio del giocatore {sum(userHand)}")
    print(f"Mazzo della cpu {cpuHand}")
    print(f"Punteggio della cpu {sum(cpuHand)}")
    repeat=input("VUOI GIOCARE ANCORA? s/n: ")
    
    if repeat!='s' and repeat!='n':
        repeat=input("Input non valido, scegliere s o n: ")
    
