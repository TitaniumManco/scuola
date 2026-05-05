import java.time.LocalDate;

public class Rivista extends Pubblicazione {
    private boolean patinata;
    
    public Rivista(String titolo, LocalDate dataPubblicazione, int numPagine, boolean patinata) {
        super(titolo, dataPubblicazione, numPagine);
        this.patinata = patinata;
    }

    public boolean isPatinata() {
        return patinata;
    }

    public void setPatinata(boolean patinata) {
        this.patinata = patinata;
    }

    public String toString(){
        return String.format("%sPatinata: %b\n", super.toString(), patinata);
    }
}
