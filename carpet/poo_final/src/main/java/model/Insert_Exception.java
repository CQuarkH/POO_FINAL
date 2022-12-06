package model;

import java.sql.SQLException;

public class Insert_Exception extends SQL_Alert {

    public Insert_Exception(Throwable cause) {
        super(cause);
        this.eMessage = "Error al insertar registros :";
        printException(this.eMessage, cause);

    }

    @Override
    public void printException(String eMessage, Throwable cause) {
        System.out.println(eMessage+cause.getMessage());
    }
}
