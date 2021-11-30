package modele.gestionFichiers.experts;

public abstract class ExpertCOR implements Expert {

    private ExpertCOR suivant;

    public ExpertCOR(ExpertCOR suivant) {
        this.suivant = suivant;
    }

    @Override
    public Object resoudre(Object o) {
        Object obj = this.resoudre1(o);

        if (obj != null)
            return obj;
        else {
            if (suivant != null)
                return this.suivant.resoudre(o);
            else
                return null;
        }
    }

    /**
     * @param o
     * @return null si o n'est pas reconnu
     */
    protected abstract Object resoudre1(Object o);

}