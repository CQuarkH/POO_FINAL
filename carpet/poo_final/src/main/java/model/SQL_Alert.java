package model;

public abstract class SQL_Alert extends Exception{
    public String eMessage;

    public SQL_Alert(Throwable cause) {

    }
    public abstract void printException(String eMessage, Throwable cause);
}
