<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <h1 class="header-title">CD List</h1>
      </header>

      <table class="product-table">
        <tr>
          <th>Description</th>
          <th>Price</th>
          <th>Action</th>
        </tr>
        <tr>
          <td>86 (the band) - True Life Songs and Pictures</td>
          <td>$14.95</td>
          <td>
            <form action="cart" method="post">
              <input type="hidden" name="action" value="add">
              <input type="hidden" name="productId" value="1">
              <input type="submit" value="Add To Cart">
            </form>
          </td>
        </tr>
        <tr>
          <td>Paddlefoot - The first CD</td>
          <td>$12.95</td>
          <td>
            <form action="cart" method="post">
              <input type="hidden" name="action" value="add">
              <input type="hidden" name="productId" value="2">
              <input type="submit" value="Add To Cart">
            </form>
          </td>
        </tr>
        <tr>
          <td>Paddlefoot - The second CD</td>
          <td>$14.95</td>
          <td>
            <form action="cart" method="post">
              <input type="hidden" name="action" value="add">
              <input type="hidden" name="productId" value="3">
              <input type="submit" value="Add To Cart">
            </form>
          </td>
        </tr>
        <tr>
          <td>Joe Rut - Genuine Wood Grained Finish</td>
          <td>$14.95</td>
          <td>
            <form action="cart" method="post">
              <input type="hidden" name="action" value="add">
              <input type="hidden" name="productId" value="4">
              <input type="submit" value="Add To Cart">
            </form>
          </td>
        </tr>
      </table>
    </main>
  </body>
</html>
