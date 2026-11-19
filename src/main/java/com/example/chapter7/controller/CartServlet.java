package com.example.chapter7.controller;

import com.example.chapter7.data.ProductIO;
import com.example.chapter7.model.Cart;
import com.example.chapter7.model.LineItem;
import com.example.chapter7.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private static final String PRODUCTS_PATH = "/WEB-INF/products.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "shop";
        }

        switch (action) {
            case "shop" -> getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            case "cart" -> getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
            case "add" -> handleAdd(request, response);
            case "update" -> handleUpdate(request, response);
            case "remove" -> handleRemove(request, response);
            case "checkout" -> handleCheckout(request, response);
            default -> getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

    private void handleAdd(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String productId = request.getParameter("productId");
        HttpSession session = request.getSession();
        Cart cart = getCart(session);

        if (productId != null) {
            Product product = ProductIO.getProduct(productId);
            if (product != null) {
                cart.addItem(new LineItem(product, 1));
                session.setAttribute("cart", cart);
            }
        }
        response.sendRedirect(request.getContextPath() + "/cart?action=cart");
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String productId = request.getParameter("productId");
        String quantityParam = request.getParameter("quantity");
        HttpSession session = request.getSession();
        Cart cart = getCart(session);

        if (productId != null && quantityParam != null) {
            try {
                int quantity = Integer.parseInt(quantityParam.trim());
                cart.updateItem(productId, quantity);
                session.setAttribute("cart", cart);
            } catch (NumberFormatException ignored) {
                // Giữ nguyên số lượng cũ nếu nhập không phải số nguyên
            }
        }
        response.sendRedirect(request.getContextPath() + "/cart?action=cart");
    }

    private void handleRemove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String productId = request.getParameter("productId");
        HttpSession session = request.getSession();
        Cart cart = getCart(session);
        if (productId != null) {
            cart.removeItem(productId);
            session.setAttribute("cart", cart);
        }
        response.sendRedirect(request.getContextPath() + "/cart?action=cart");
    }

    private void handleCheckout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = getCart(session);
        if (!cart.getItems().isEmpty()) {
            request.setAttribute("cart", cart);
            session.removeAttribute("cart");
        }
        getServletContext().getRequestDispatcher("/checkout.jsp").forward(request, response);
    }

    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
