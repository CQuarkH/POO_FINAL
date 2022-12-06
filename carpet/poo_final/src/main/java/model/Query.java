package model;

public enum Query {

    SELECTALL ("SELECT * FROM recetas;"),
    SELECT_WHERE_LIKE("SELECT * FROM recetas WHERE ingredientes like ");

    final String sql;

    Query(String sql) {
        this.sql = sql;
    }
}
