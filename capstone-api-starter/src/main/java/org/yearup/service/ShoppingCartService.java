package org.yearup.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;

@Service
public class ShoppingCartService
{
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService)
    {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public ShoppingCart getByUserId(int userId)
    {
        // load the user's cart rows, look up each product, and build the ShoppingCart
        List<CartItem> items = shoppingCartRepository.findByUserId(userId);
        ShoppingCart cart = new ShoppingCart();

        // for each row, look up the product and create a ShoppingCartItem
        for (CartItem item : items) {
            Product product = productService.getById(item.getProductId());
//
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

            shoppingCartItem.setProduct(product);
            shoppingCartItem.setQuantity(item.getQuantity());

            cart.add(shoppingCartItem);
        }

        return cart;
    }

    // add additional methods here

    public ShoppingCart addProductToCart(int userId, int productID)
    {
        // checks to see if item is currently in the cart, if it is, it will increment the quantity by 1,
        // if not, it will add a new item to the cart with a quantity of 1
        CartItem item = shoppingCartRepository.findByUserIdAndProductId(userId, productID);

        if (item == null)
        {
            item = new CartItem();
            item.setUserId(userId);
            item.setProductId(productID);
            item.setQuantity(1);
        }
        else
        {
            int currentQuantity = item.getQuantity();
            item.setQuantity(currentQuantity + 1);
        }

        shoppingCartRepository.save(item);

        return getByUserId(userId);
    }

    public ShoppingCart updateProductInCart(int userId, int productID, int quantity)
    {
        CartItem item = shoppingCartRepository.findByUserIdAndProductId(userId, productID);

        if (item == null)
        {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Product not found in cart");
        }
        if (quantity < 1)
        {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Quantity must be at least 1");
        }

        item.setQuantity(quantity);
        shoppingCartRepository.save(item);

        return getByUserId(userId);
    }

    @Transactional
    public boolean clearCart(int userId)
    {
        // here all my values were correct. but for some reason the userid was null
        // from the ShoppingCartController everytime.
        // after using the debuggung tool, i found out the reason why userId was null
        // was because the username it was receiving was "testuser" and the "testuser" i found
        // did not exist in the database. After going in insomnia and changin the Auth to the "user"
        // it solved the problem
        shoppingCartRepository.deleteByUserId(userId);

        return true;
    }
}
