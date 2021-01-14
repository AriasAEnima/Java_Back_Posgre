package julianarias.smartjsp_prueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ViolationDataIntegrityException extends Exception{

    private static final long serialVersionUID = 2L;
    public static final String DATA_ERROR= "Error en los datos, verifique los tipos y/o ya existe(n) la(s) llave(s)";

    public ViolationDataIntegrityException(String message){
        super(message);
    }
}