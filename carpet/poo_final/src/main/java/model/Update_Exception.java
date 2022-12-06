package model;

public class Update_Exception extends SQL_Alert {

    public Update_Exception(Throwable cause) {
        super(cause);
        this.eMessage = "Error al actualizar registros :";
        System.out.println(eMessage);
        printException(this.eMessage, cause);


    }

    @Override
    public void printException(String eMessage, Throwable cause) {
        System.out.println(eMessage+cause.getMessage());

    }
}
