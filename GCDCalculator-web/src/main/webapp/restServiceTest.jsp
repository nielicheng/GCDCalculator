<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test</title>
    </head>
    <body>
        <h1>Rest Service Test!</h1>
        <form method="post" action="rest/gcdService/create" enctype="application/x-www-form-urlencoded">
        	i1: <input type="text" name="i1"> <br/>
        	i2: <input type="text" name="i2"> <br/>
        	<input type="submit" value="Submit">
        </form>
    </body>
</html>