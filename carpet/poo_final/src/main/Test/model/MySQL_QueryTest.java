package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySQL_QueryTest {

    MySQL_Query queryTest;
    Receta aux;

    String busqueda;

    String selectBusqueda;

    String [] splitBusqueda;

    StringBuilder str;

    List<Receta> auxRecetas;

    @BeforeEach
    void setUp() {
        queryTest = new MySQL_Query();
        aux = new Receta(1, "RecetaTest", "Pan, Agua", "Test, Prueba", "Test...");
        queryTest.connect();

        busqueda = "azucar sal aceite";
        //test desde un solo ingrediente, ya que el resto lo realiza por defecto MariaDB
        selectBusqueda = "aceite";

        splitBusqueda = busqueda.split(" ");
        str = new StringBuilder("SELECT * FROM recetas WHERE ingredientes LIKE ? ");

        auxRecetas = new ArrayList<>();
        auxRecetas.add(new Receta(1, "test1", "agua azucar sal", "test1", "---------"));
        auxRecetas.add(new Receta(2, "test2", "azucar sal", "test2", "---------"));
    }

    @AfterEach
    void tearDown() throws Delete_Exception{
        queryTest.delete(aux);
        queryTest = null;
    }

    @Test
    void getTblRecetas() {
    }

    @Test
    void connect() {
        assertEquals(1, queryTest.connect());
    }

    @Test
    void insert() throws Insert_Exception{
        assertEquals(1, queryTest.insert(aux));

    }

    @Test
    void select() {
        queryTest.select(selectBusqueda);
        queryTest.getTblRecetas().forEach(x->
                System.out.println(x.getIngredientes()));
        assertTrue(queryTest.getTblRecetas().stream().anyMatch(x->x.getIngredientes().toLowerCase().contains(selectBusqueda.toLowerCase())));
    }

    @Test
    void delete() throws Delete_Exception{
        assertEquals(1, queryTest.delete(aux));
    }

    @Test
    void update() throws Update_Exception{
        assertEquals(1, queryTest.update(aux));
    }

    @Test
    void split() {
        assertEquals(Arrays.toString(splitBusqueda), Arrays.toString(queryTest.split(busqueda)));
    }

    @Test
    void queryString() {
        assertEquals("SELECT * FROM recetas WHERE ingredientes LIKE ?  OR  ?  OR  ? ", queryTest.queryString(str, splitBusqueda));
    }

    @Test
    void sortedRecetasByIngredients() {
        assertNotEquals(auxRecetas.get(0), queryTest.sortedRecetasByIngredients(auxRecetas).get(0));
        System.out.println("Unsorted recetas by ingredients");
        auxRecetas.forEach(x-> System.out.println(x.getIngredientes()));
        System.out.println("Sorted recetas by ingredients");
        queryTest.sortedRecetasByIngredients(auxRecetas).forEach(x-> System.out.println(x.getIngredientes()));
    }
}