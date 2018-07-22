<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Крестики Нолики</title>

      <style type="text/css">
          input {
              width: 30px;
              height: 30px;
              margin: 5px;
              padding: 0;
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

        function reset() {
            $.ajax({
                type: 'GET',
                url: '/field/fieldReset'
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
          <input type="button" onclick="x(2)" id="button2" value="${sessionScope.get("fieldMap").get("2")}"/>
          <input type="button" onclick="x(3)" id="button3" value="${sessionScope.get("fieldMap").get("3")}"/>
        </td>
      </tr>
      <tr>
        <td>
          <input onclick="x(4)" type="button" id="button4" value="${sessionScope.get("fieldMap").get("4")}"/>
          <input type="button" onclick="x(5)" id="button5" value="${sessionScope.get("fieldMap").get("5")}"/>
          <input type="button" onclick="x(6)" id="button6" value="${sessionScope.get("fieldMap").get("6")}"/>
        </td>
      </tr>
      <tr>
        <td>
          <input onclick="x(7)" type="button" id="button7" value="${sessionScope.get("fieldMap").get("7")}"/>
          <input type="button" onclick="x(8)" id="button8" value="${sessionScope.get("fieldMap").get("8")}"/>
          <input type="button" onclick="x(9)" id="button9" value="${sessionScope.get("fieldMap").get("9")}"/>
        </td>
      </tr>
    </table>
  <p>${sessionScope.get("checkWin")}<p/>
  <input type="button" onclick="reset()" value="Сброс" style="height: 30px;width: 120px">

  </body>
</html>