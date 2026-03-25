import random
class Game:
    def __init__(self, lista_personaggi, lista_domande):
        self.lista_personaggi = lista_personaggi
        self.lista_domande = lista_domande
        self.numero_domanda = 0
        self.punteggio = 0
        self.sbagliato = 0
        self.numero_tentativi = 0
        self.flag_empty = False



    def scegli_personaggio(self):
        return random.choice(self.lista_personaggi)
    


    def next_question(self):
        if not self.lista_domande:                      # se non rimangono domande nel pool, il player viene forzato nel guess
            if not self.flag_empty:
                print("")
                print("Domande Finite!")
                self.flag_empty = True
            return 0
        
        domande = {}
        menu = []
        
        for d in self.lista_domande:
            if d.attributo not in domande:
                domande[d.attributo] = []               # crea un dict con chiave = attributo delle domande e
            domande[d.attributo].append(d)              # valore = tutte le domande con quel attributo

        for d in domande.values():
            menu.append(random.choice(d))               # sceglie domande random 

        print("")
        print(("Scegli una domanda: (0 per indovinare)"))
        for i in range(len(domande)):
            print(f'{i + 1}. {menu[i].testo}')

        risposta = int(input(""))

        while risposta < 0 or risposta > len(menu):
            print("Opzione non valida")                 # richiede l'input in caso di input errato
            risposta = int(input(""))

        if(risposta == 0):
            return risposta
        else:
            domanda_scelta = menu[risposta - 1]

            self.lista_domande.remove(domanda_scelta)       # rimuove la domanda scelta

            return domanda_scelta



    def check_answer(self, domanda):
        if domanda.controlla(self.personaggio_segreto):
            print("Sì")
        else:
            print("No")



    def guess_personaggio(self):
        print("")
        print("Chi pensi che sia?")
        scelta = input("")

        return scelta.strip().lower() in self.personaggio_segreto.nome.lower().split()


    def play(self):
        self.personaggio_segreto = self.scegli_personaggio()
        x = True
        
        while x:
            if(self.sbagliato >= 5):
                print("")
                print(f"Hai perso!, il personaggio era {self.personaggio_segreto.nome}")
                x = False
                break

            scelta = self.next_question()
            if scelta == 0:
                self.numero_tentativi += 1
                if self.guess_personaggio():
                    print("Corretto! Hai indovinato il personaggio!")
                    x = False
                else:
                    self.sbagliato += 1
                    print("Sbagliato")
            else:
                self.numero_domanda += 1
                self.check_answer(scelta)

        print("")
        print(f"Domande poste: {self.numero_domanda}")
        print(f"Tentativi effettuati: {self.numero_tentativi}")