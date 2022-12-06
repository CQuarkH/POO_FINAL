package model;

public interface SQL_Query {
    int connect();
    int insert(Receta receta) throws Insert_Exception;
    int select(String busqueda);
    int update(Receta receta) throws Update_Exception;
    int delete(Receta receta) throws Delete_Exception;
}
