import 'Dipendente.dart';

class Docente extends Dipendente{
  int _oreDiDocenza;

  void set oreDiDocenza(int val){
    if(val >= 0){
      _oreDiDocenza = val;
    }
    else
      _oreDiDocenza = 0;
  }
  
  int get oreDiDocenza => _oreDiDocenza;

  Docente(this._oreDiDocenza, String nome, String sesso, String dataDiNascita, double stipendioBase)
    : super(nome, sesso, dataDiNascita, stipendioBase);


  @override
  double calcolaStipendio(){
    return this.stipendioBaseMensile!;
  }

  @override
  String toString() {
    return "Docente\n" + super.toString() + "\nOre di docenza: $_oreDiDocenza";
  }
}