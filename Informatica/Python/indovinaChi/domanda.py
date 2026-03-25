class Domanda:
    def __init__(self, testo, attributo, valore_atteso):
        self.testo = testo
        self.attributo = attributo
        self.valore_atteso = valore_atteso

    def controlla(self, personaggio):
        if self.attributo == "professione":
            return personaggio.professione == self.valore_atteso
        elif self.attributo == "nazionalita":
            return personaggio.nazionalita == self.valore_atteso
        elif self.attributo == "epoca":
            return personaggio.epoca == self.valore_atteso
        else:
            return personaggio.genere == self.valore_atteso