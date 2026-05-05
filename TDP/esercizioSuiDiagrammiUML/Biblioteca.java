import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Pubblicazione> collezione;

    public Biblioteca(){
        collezione = new ArrayList<Pubblicazione>();
    }

    public Pubblicazione cercaPubblicazione(Pubblicazione b){
        for (Pubblicazione p : collezione) {
            if(p.getTitolo().equals(b.getTitolo()) && p.getNumPagine()==b.getNumPagine()){
                return p;
            }
        }
        return null;
    }

    public boolean addPubbilcazione(Pubblicazione b){
        if (cercaPubblicazione(b) != null) {
            return false;
        }
        collezione.add(b);
        return true;
    }

    public boolean removePubblicazione(Pubblicazione b){
        Pubblicazione trovata = cercaPubblicazione(b);
        if(trovata == null){
            return false;
        }
        collezione.remove(trovata);
        return true;
    }

    public Pubblicazione prestito(Pubblicazione b, String recapito){

        Pubblicazione esistente = cercaPubblicazione(b);

        if(esistente!=null && esistente.isDisponibile()==true){
            if(esistente instanceof Libro){
                esistente.setMesiRestituzione(2);
            }
            else{
                esistente.setMesiRestituzione(1);
            }
            esistente.setRecapitoLettore(recapito);
            esistente.setDisponibile(false);
            return esistente;
        }
        else if(esistente!=null && esistente.isDisponibile()==false){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String s = LocalDate.now().plusMonths(esistente.getMesiRestituzione()).format(formatter);
            System.out.println(String.format("Pubblicazione non disponibile, data prevista: %s", s));
            return null;
        }

        System.out.println("Non è presente la pubblicazione richiesta");
        return null;
    }

    public boolean restituzione(Pubblicazione b){
        Pubblicazione esistente = cercaPubblicazione(b);

        if(esistente!=null){
            esistente.setDisponibile(true);
            esistente.setMesiRestituzione(0);
            esistente.setRecapitoLettore("");
            return true;
        }
        
        return false;
    }

    @Override
    public String toString() {
        String s="";
        
        for(Pubblicazione x : collezione){
            s+=x.toString();
        }
        return s;
    }
}
