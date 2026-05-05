import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pubblicazione {
    public final int id;
    public String titolo;
    public LocalDate dataPubblicazione;
    private int numPagine;
    private boolean disponibile;
    private int mesiRestituzione;
    private String recapitoLettore;
    public static int num=0;

    public Pubblicazione(String titolo, LocalDate dataPubblicazione, int numPagine) {
        this.titolo = titolo;
        this.dataPubblicazione = dataPubblicazione;
        this.numPagine = numPagine;
        disponibile=true;
        mesiRestituzione=0;
        recapitoLettore="";
        num++;
        this.id=num;
    }

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(LocalDate dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public int getNumPagine() {
        return numPagine;
    }

    public void setNumPagine(int numPagine) {
        this.numPagine = numPagine;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    public int getMesiRestituzione() {
        return mesiRestituzione;
    }

    public void setMesiRestituzione(int mesiRestituzione) {
        this.mesiRestituzione = mesiRestituzione;
    }

    public String getRecapitoLettore() {
        return recapitoLettore;
    }

    public void setRecapitoLettore(String recapitoLettore) {
        this.recapitoLettore = recapitoLettore;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String s = dataPubblicazione.format(formatter);
        return String.format("\nID: %d\nTitolo: %s\nData di Pubblicazione: %s\nNumero di Pagine %d\nDipsonibilità: %b\n", id, titolo, s, numPagine, disponibile);
    }
    
}
