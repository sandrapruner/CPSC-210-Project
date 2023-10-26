package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WIshListTest {
    private WishList testwishlist;

    @BeforeEach
    void runBefore() {
        testwishlist = new WishList("wishlist");
    }

    @Test
    void changeName() {
        assertEquals("wishlist", testwishlist.getName());
        testwishlist.changeName("wish");
        assertEquals("wish", testwishlist.getName());
    }
}
