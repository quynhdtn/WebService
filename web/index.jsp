<%--
  Created by IntelliJ IDEA.
  User: quynhdo
  Date: 08/09/15
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <div class="container">
    <div class="textareacontainer">
      <form   action="pg" method="post">

        <div class="textarea">
          <div class="headerText" style="width:auto;float:left;">Input a text    :</div>
          <div class="headerBtnDiv" style="width:auto;float:right;margin-top:8px;margin-right:2.4%;"><button class="submit" type="submit" value="Submit">See results</button></div>
          <div class="textareawrapper">
            <textarea autocomplete="off" class="code_input" id="textareaCode" wrap="logical" xrows="500" xcols="500" maxlength="4700" name="text" ></textarea>

          </div>
        </div>
      </form>

    </div>
  </div>

  </body>
</html>
