package Shoppingbasket;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingBasketTest {

    ShoppingBasket s;
    ProductDAO dao;
    Product p;
    Product p2;

    @BeforeEach
    void setup() {
        dao = mock(ProductDAO.class);
        p = new Product(1, "Iphone", 3000);
        p2 = new Product(2, "Samsung", 2000);

        when(dao.findByName(anyString())).thenReturn(p);
        when(dao.findById(anyInt())).thenReturn(p2);
        when(dao.findById(7)).thenThrow(new IllegalArgumentException());
        when(dao.findByName("Samsung")).thenThrow(new IllegalArgumentException());

        s = new ShoppingBasket();
        s.setProductDAO(dao);

    }

    @Test
    void exceptionTestAddById() {

        assertThrows(IllegalArgumentException.class, () -> s.addById(1, 7));
    }

    @Test
    void exceptionTestAddByName() {

        assertThrows(IllegalArgumentException.class, () -> s.addByName(1, "Samsung"));
    }

    @Test
    void testToString() {
        s.addByName(1, "Iphone");
        s.addById(2, 2);
        assertEquals("[[1*Iphone], [2*Samsung]]", s.toString());
    }

    @Test
    void testAddByName() {
        s.addByName(2, "Iphone");
        assertTrue(s.toString().contains("2*Iphone"));
    }

    @Test
    void testAddById() {
        s.addById(2, 2);
        assertTrue(s.toString().contains("2*Samsung"));
    }

    @Test
    void clearTest() {
        s.addById(1, 2);
        s.addByName(2, "Iphone");

        s.clear();
        assertEquals("[]", s.toString());
    }

    @Test
    void getTotalCostTest() {
        s.addByName(2, "Iphone");
        s.addById(2, 2);
        assertEquals(10000, s.getTotalCost());
    }


}