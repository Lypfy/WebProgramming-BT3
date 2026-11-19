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
        <h1 class="header-title">Checkout</h1>
      </header>

      <p>Thank you for your order</p>

      <c:if test="${not empty cart and not empty cart.items}">
        <table class="cart-table">
          <tr>
            <th>Quantity</th>
            <th>Description</th>
            <th>Price</th>
            <th>Amount</th>
          </tr>
          <c:forEach var="item" items="${cart.items}">
            <tr>
              <td><c:out value="${item.quantity}"/></td>
              <td><c:out value="${item.product.name}"/></td>
              <td><c:out value="${item.product.priceCurrencyFormat}"/></td>
              <td><c:out value="${item.totalCurrencyFormat}"/></td>
            </tr>
          </c:forEach>
          <tr>
            <td colspan="3"><strong>Total</strong></td>
            <td><strong><c:out value="${cart.totalCurrencyFormat}"/></strong></td>
          </tr>
        </table>
      </c:if>

      <form action="${pageContext.request.contextPath}/cart" method="get">
        <input type="hidden" name="action" value="shop">
        <input type="submit" value="Continue Shopping">
      </form>
    </main>
  </body>
</html>
