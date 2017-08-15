package core.util;

/**
 * Created by Ivans on 8/15/2017.
 */
public class FuncionesUtil {

    public static void removePositionArray(String[] nameArray, int... positions){
        for (int position : positions)
            nameArray[position] = null;
    }
}
