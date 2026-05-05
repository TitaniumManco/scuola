abstract class Dipendente {
  String _nome;
  String _sesso;
  String _dataDiNascita;
  double? _stipendioBaseMensile;

  void set nome(String s){
    if(s.isNotEmpty){
      _nome = s;
    }
    else
      _nome = "senza nome";
  }

  String get nome => _nome;

  void set sesso(String s){
    if(s.isNotEmpty){
      _sesso = s;
    }
    else
      _sesso = "non specificato";
  }

  String get sesso => _sesso;

  void set stipendioBaseMensile(double? val){
    if(val != null && val >= 0){
      _stipendioBaseMensile = val;
    }
    else
      _stipendioBaseMensile = 0;
  }

  double? get stipendioBaseMensile => _stipendioBaseMensile;

  void set dataDiNascita(String s){
    if(s.isNotEmpty){
      _dataDiNascita = s;
    }
    else
      _dataDiNascita = "non specificato";
  }

  String get dataDiNascita => _dataDiNascita;

  Dipendente(this._nome, this._sesso, this._dataDiNascita, double stipendioBaseMensile){
    this.stipendioBaseMensile = stipendioBaseMensile;
  }

  double calcolaStipendio(); 

  @override
  String toString() {
    return "Nome: $_nome\nSesso: $_sesso\nData di Nascita: $_dataDiNascita\nStipendio base mensile: $_stipendioBaseMensile";
  }
}