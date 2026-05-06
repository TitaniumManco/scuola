import java.time.LocalDate;

public class Libro extends Pubblicazione {
    private String ISBN;

    public Libro(String titolo, LocalDate dataPubblicazione, int numPagine, String iSBN) {
        super(titolo, dataPubblicazione, numPagine);
        ISBN = iSBN;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public String toString(){
        return String.format("%sCodice ISBN: %s\n", super.toString(), ISBN);
    }
}
