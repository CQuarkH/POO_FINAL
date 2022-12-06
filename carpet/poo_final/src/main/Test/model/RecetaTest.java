package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecetaTest {
    Receta test;

    @BeforeEach
    void setUp() {
        test = new Receta(1, "Pasas al Ron", "Pasas, Ron, Azucar", "Coctel, Tragos", "Prepara las pasas al ron, colocandolas sobre...");
    }

    @AfterEach
    void tearDown() {
        test = null;
    }

    @Test
    void getNombre() {
        assertEquals("Pasas al Ron", test.getNombre());
    }

    @Test
    void getIngredientes() {
        assertEquals("Pasas, Ron, Azucar", test.getIngredientes());
    }

    @Test
    void getEtiquetas() {
        assertEquals("Coctel, Tragos", test.getEtiquetas());
    }

    @Test
    void getReceta() {
        assertEquals("Prepara las pasas al ron, colocandolas sobre...", test.getReceta());
    }

    @Test
    void getId() {
        assertEquals(1, test.getId());
    }
}