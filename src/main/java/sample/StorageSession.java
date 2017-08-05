package sample;

/**
 * Created by WAMS-10 on 30/07/2017.
 */
public class StorageSession {

    private String nombreUsurio;
    private int nivelUsuario;
    private int idUsuario;
    private static StorageSession soyUnico;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private StorageSession(int idUsuario, String nombreUsurio, int nivelUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsurio = nombreUsurio;
        this.nivelUsuario = nivelUsuario;
        System.out.println("Mi nombre es: " + this.idUsuario + this.nombreUsurio + this.nivelUsuario);
    }

    public static StorageSession getSingletonInstance(int idUsuario, String nombreUsurio, int nivelUsuario) {
        if (soyUnico == null) {
            soyUnico = new StorageSession(idUsuario, nombreUsurio, nivelUsuario);
        } else {
            System.out.println("No se puede crear el objeto " + idUsuario + nombreUsurio + nivelUsuario);
        }
        return soyUnico;
    }

    public String getNombreUsurio() {
        return nombreUsurio;
    }

    public void setNombreUsurio(String nombreUsurio) {
        this.nombreUsurio = nombreUsurio;
    }

    public int getNivelUsuario() {
        return nivelUsuario;
    }

    public void setNivelUsuario(int nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
