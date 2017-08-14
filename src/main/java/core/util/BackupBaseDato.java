package core.util;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;

/**
 * Created by Ivans on 8/13/2017.
 */
public class BackupBaseDato {

    private String yourDBName;
    private String yourUserName;
    private String yourUserPassword;
    private Class<?> cls;

    public BackupBaseDato(String yourDBName, String yourUserName, String yourUserPassword, Class<?> cls) {
        this.yourDBName = yourDBName;
        this.yourUserName = yourUserName;
        this.yourUserPassword = yourUserPassword;
        this.cls = cls;
    }

    public static void main(String[] args) {
        BackupBaseDato backupBaseDato = new BackupBaseDato("rrhh", "Oscar", "123", BackupBaseDato.class);
        //backupBaseDato.backupdbtosql();
        backupBaseDato.restore();
    }

    public void backupdbtosql() {
        try {
            Process p = Runtime
                    .getRuntime()
                    .exec("C:\\xampp\\mysql\\bin\\mysqldump -u Oscar -p123 rrhh");

            InputStream is = p.getInputStream();
            FileOutputStream fos = new FileOutputStream("rrhh.sql");
            byte[] buffer = new byte[1000];

            int leido = is.read(buffer);
            while (leido > 0) {
                fos.write(buffer, 0, leido);
                leido = is.read(buffer);
            }

            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void restore() {
        try {
            Process p = Runtime
                    .getRuntime()
                    .exec("C:\\xampp\\mysql\\bin\\mysql -u Oscar -p 123 rrhh");

            OutputStream os = p.getOutputStream();
            FileInputStream fis = new FileInputStream("rrhh.sql");
            byte[] buffer = new byte[1000];

            int leido = fis.read(buffer);
            while (leido > 0) {
                os.write(buffer, 0, leido);
                leido = fis.read(buffer);
            }

            os.flush();
            os.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restoredbfromsql(String s) {
        try {
            /*NOTE: String s is the mysql file name including the .sql in its name*/
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = cls.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            /*NOTE: Creating Database Constraints*/
            String dbName = yourDBName;
            String dbUser = yourUserName;
            String dbPass = yourUserPassword;

            /*NOTE: Creating Path Constraints for restoring*/
            String restorePath = jarDir + "\\backup" + "\\" + s;

            /*NOTE: Used to create a cmd command*/
            /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
            String[] executeCmd = new String[]{"mysql", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0)
                System.out.println("Successfully restored from SQL : " + s);
            else
                System.out.println("Error at restoring");
        } catch (URISyntaxException | IOException | InterruptedException | HeadlessException ex) {
            System.out.println("Error at restoredbfromsql" + ex.getMessage());
        }
    }
}
