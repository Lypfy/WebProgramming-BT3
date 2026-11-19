<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HttpSession Practice</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/styles/main.css"
    />
  </head>
  <body>
    <main>
      <header>
        <h1 class="header-title">Your cart</h1>
      </header>

      <c:if test="${not empty sessionScope.checkoutMessage}">
        <p class="checkout-msg"><c:out value="${sessionScope.checkoutMessage}"/></p>
        <c:remove var="checkoutMessage" scope="session"/>
      </c:if>

   <c:choose>
        <c:when test="${empty sessionScope.cart or empty sessionScope.cart.items}">
            <p>Your cart is empty.</p>
        </c:when>
        <c:otherwise>
            <table class="cart-table">
                <tr>
                    <th>Quantity</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Amount</th>
                    <th></th>
                </tr>
                <c:forEach var="item" items="${sessionScope.cart.items}">
                    <tr>
                        <td>
                            <form action="${pageContext.request.contextPath}/cart" method="post">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="productId" value="${item.product.productId}">
                                <input type="text" name="quantity" value="${item.quantity}" size="2">
                                <input type="submit" value="Update">
                            </form>
                        </td>
                        <td><c:out value="${item.product.name}"/></td>
                        <td><c:out value="${item.product.priceCurrencyFormat}"/></td>
                        <td><c:out value="${item.totalCurrencyFormat}"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/cart" method="post">
                                <input type="hidden" name="action" value="remove">
                                <input type="hidden" name="productId" value="${item.product.productId}">
                                <input type="submit" value="Remove Item">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <p><strong>To change the quantity</strong>, enter the new quantity and click on the Update button.</p>
        </c:otherwise>
    </c:choose>
    <form action="${pageContext.request.contextPath}/cart" method="get" class="continue-btn">
        <input type="hidden" name="action" value="shop">
        <input type="submit" value="Continue Shopping">
    </form>
    <c:if test="${not empty sessionScope.cart and not empty sessionScope.cart.items}">
        <form action="${pageContext.request.contextPath}/cart" method="post">
            <input type="hidden" name="action" value="checkout">
            <input type="submit" value="Checkout">
        </form>
    </c:if>
</main>
</body>
</html>