package modele.gestionFichiers.experts;

public interface Expert
{
    /**
     @param o : donn�e du probl�me � r�soudre
     @return la solution ou null en cas d'�chec
     */
    public Object resoudre (Object o);
}
