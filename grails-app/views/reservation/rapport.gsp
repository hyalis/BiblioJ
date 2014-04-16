
<%@ page import="biblioj.Reservation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Rapport de reservation</title>
	</head>
	<body>
		<div id="rapport">
			Vous avez le code :<b> ${code} </b><br><br>
			Venez retirer votre panier au plus tard le :<b> ${dateRet} </b><br><br>
			<g:link controller="reservation" action="list">Liste de toutes les réservations</g:link></li>
		</div>
		
	</body>
</html>
