package model;

import java.sql.SQLException;

public class Delete_Exception extends SQL_Alert {

    public Delete_Exception(Throwable cause) {
        super(cause);
        this.eMessage = "Error al eliminar registros :";
        printException(this.eMessage, cause);
    }

    @Override
    public void printException(String eMessage, Throwable cause) {
        System.out.println(eMessage+cause.getMessage());

    }
}
