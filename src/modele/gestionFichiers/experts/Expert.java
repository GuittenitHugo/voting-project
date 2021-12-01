package modele.gestionFichiers.experts;

public interface Expert
{
    /**
     @param o : donnée du problème à résoudre
     @return la solution ou null en cas d'échec
     */
    public Object resoudre (Object o);
}
