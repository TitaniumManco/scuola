from domanda import Domanda
from personaggio import Personaggio
from data import dati_personaggi, dati_domande
from game import Game

print('Benvenuto al gioco "Chi è il Personaggio?"')
print('Ho scelto un personaggio segreto. Cerca di indovinare chi è in massimo 5 tentativi!')

lista_domande = []
lista_personaggi = []

for i in dati_domande:
    lista_domande.append(Domanda(i["testo"], i["attributo"], i["valore_atteso"]))

for i in dati_personaggi:
    lista_personaggi.append(Personaggio(i["nome"], i["professione"], i["nazionalita"], i["epoca"], i["genere"]))


gioco = Game(lista_personaggi, lista_domande)

gioco.play()