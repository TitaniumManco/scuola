import 'Dipendente.dart';

class Impiegato extends Dipendente{
  int _livello;
  double _oreDiStraordinario;
  static double _retribuzioneOraria = 15.0;
  
  void set livello(int val){
    if(val >= 0){
      _livello = val;
    }
    else
      _livello = 0;
  }
  
  int get livello => _livello;

  void set oreDiStraordinario(double val){
    if(val >= 0){
      _oreDiStraordinario = val;
    }
    else
      _oreDiStraordinario = 0;
  }
  
  double get oreDiStraordinario => _oreDiStraordinario;

  static double get retribuzioneOraria => _retribuzioneOraria;

  Impiegato(this._livello, this._oreDiStraordinario, String nome, String sesso, String dataDiNascita, double stipendioBase)
    : super(nome, sesso, dataDiNascita, stipendioBase);


  @override
  double calcolaStipendio(){
    return this.stipendioBaseMensile! + (this._oreDiStraordinario * _retribuzioneOraria);
  }

  @override
  String toString() {
    return "Impiegato\n" + super.toString() + "\nLivello: $_livello\nOre Di Straordinario: $_oreDiStraordinario";
  }
}