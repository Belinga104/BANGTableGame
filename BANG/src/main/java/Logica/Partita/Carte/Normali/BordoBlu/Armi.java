package Logica.Partita.Carte.Normali.BordoBlu;

/**
 *Questa classe serve per creare le armi presenti nel gioco
 */
public abstract class Armi extends BordoBlu{

    private int distanza;

    /**
     * Inizializza le armi con il seme e la loro distanza
     * @param seme Il seme dell'arma
     * @param distanza la distanza dell'arma
     */
    public Armi(String seme,int distanza) {
        super(seme);
        this.distanza = distanza;
    }

    /**
     * Ritorna la distanza dell'arma
     * @return la distanza dell'arma
     */
    public int getDistanza() {
        return distanza;
    }

    /**
     * Cambia la distanza dell'arma
     * @param distanza la nuova dostanza dell'arma
     */
    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }

    /**
     * Ritorna tutta la descrizione dell'arma
     * @return La descrizione dell'arma
     */
    @Override
    public String toString() {
        return  " " + getClass().getSimpleName() + " (Arma) " + getSeme() + " Distanza: " + getDistanza();
    }
}
