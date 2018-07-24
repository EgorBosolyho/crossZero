<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Game</title>

      <style type="text/css">
          input {
              width: 25px;
              height: 25px;
          }
      </style>

      <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
      <script>

        function x(number) {
            $.ajax({
                type: 'GET',
                url: '/field/fieldExample?inputId='+number+''
            });
            location.reload();
        }
      </script>
  </head>
  <body>

    <table>
      <tr>
        <td>
          <input type="button" onclick="x(1)" id="button1" value="${sessionScope.get("fieldMap").get("1")}"/>
          <input type="button" onclick="x(2)" id="button2" value="${sessionScope.get("fieldMap").get("1")}"/>
          <input type="button" onclick="x(3)" id="button3" value="${sessionScope.get("fieldMap").get("1")}"/></td>
      </tr>
      <tr>
        <td>
          <input onclick="x(4)" type="button" id="button4" value="${sessionScope.get("fieldMap").get("1")}"/>
          <input type="button" onclick="x(5)" id="button5" value="${sessionScope.get("fieldMap").get("1")}"/>
          <input type="button" onclick="x(6)" id="button6" value="${sessionScope.get("fieldMap").get("1")}"/>
        </td>
      </tr>
      <tr>
        <td>
          <input onclick="x(7)" type="button" id="button7" value="${sessionScope.get("fieldMap").get("1")}"/>
          <input type="button" onclick="x(8)" id="button8" value="${sessionScope.get("fieldMap").get("1")}"/>
          <input type="button" onclick="x(9)" id="button9" value="${sessionScope.get("fieldMap").get("1")}"/>
        </td>
      </tr>
    </table>

  </body>
</html>
