import 'Dipendente.dart';
import 'Docente.dart';
import 'Impiegato.dart';

void main() {
  // Docente
  Docente docente = Docente(18, "Mario Rossi", "M", "15/03/1980", 2500.0);
  print("=== DOCENTE ===");
print(docente);
  print("Stipendio calcolato: ${docente.calcolaStipendio()} €\n");

  // Impiegato con straordinari
  Impiegato impiegato = Impiegato(3, 10, "Anna Bianchi", "F", "10/12/1985", 1800.0);
  print("=== IMPIEGATO ===");
  print(impiegato);
  print("Stipendio calcolato: ${impiegato.calcolaStipendio()} €\n");
  
  // Polimorfismo
  print("=== POLIMORFISMO ===");
  List<Dipendente> dipendenti = [docente, impiegato];
  for (var d in dipendenti) {
    print("${d.nome}: ${d.calcolaStipendio()} €");
  }
}