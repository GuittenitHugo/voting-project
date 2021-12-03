package modele.gestionFichiers.metier;

public enum Extension {

    CSV(".csv");

    private String extension;

    Extension(String s){
        extension = s;
    }

    public String getExtension(){return extension;}

}
