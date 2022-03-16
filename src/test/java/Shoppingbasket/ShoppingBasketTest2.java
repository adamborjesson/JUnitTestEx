package Shoppingbasket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingBasketTest2 {

    ShoppingBasket s;
    MockDao m;

    @BeforeEach
    void setup() {

        m = new MockDao();
        s = new ShoppingBasket();
        s.setProductDAO(m);
    }

    @Test
    void exceptionTestAddByName() {

        assertThrows(IllegalArgumentException.class, () -> s.addByName(1, "Samsung"));
    }

    @Test
    void exceptionTestAddById() {

        assertThrows(IllegalArgumentException.class, () -> s.addById(1, 5));
    }

    @Test
    void testToString() {

        s.addByName(1, "Iphone");
        s.addById(2, 1);

        assertEquals("[[1*Iphone], [2*Samsung]]", s.toString());
    }



    @Test
    void testAddByName() {

        s.addByName(1, "Iphone");
        assertTrue(s.toString().contains("1*Iphone"));
    }

    @Test
    void testAddById() {

        s.addById(1, 1);
        assertTrue(s.toString().contains("1*Samsung"));

    }

    @Test
    void clearTest() {
        s.addByName(1, "Iphone");
        s.addById(2, 1);

        s.clear();
        assertEquals("[]", s.toString());
    }

    @Test
    void getTotalCostTest() {
        s.addByName(2, "Iphone");
        s.addById(2, 1);
        assertEquals(10000, s.getTotalCost());
    }


    public static class MockDao implements ProductDAO{

        @Override
        public Product findById(int id) {

            if(id == 1)
                return new Product(1, "Samsung", 2000);

            else return null;
        }

        public Product findByName(String name) {

            if(name == "Iphone")
                return new Product(2, "Iphone", 3000);

            else return null;

        }

        @Override
        public List<Product> findAll() {

            return null;
        }

        @Override
        public List<Product> findCheaperThan(int lowprice) {

            return null;
        }

        @Override
        public boolean isInStock(int id) {

            return false;
        }

        @Override
        public boolean delete(int id) {

            return false;
        }

        @Override
        public void raiseAllPrices(double percent) {

        }
    }
}