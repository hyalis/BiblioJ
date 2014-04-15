<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<style type="text/css" media="screen">
			#panier{
				position: fixed;
				right: 15px;
				margin-top : 20px;
				padding: 5px;
				float : right;
				width : 200px;
				height : auto;
				text-align : left;
				background-color: #747474;
				color: white;
			}
			
			#panier a{
				color: white;
			}
		</style>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
		<div id="panier"><b>Votre panier :</b><br>
			<%
				String livresDuPanier = ''
				for(int i = 1; session.panier != null && i<session.panier.size(); i++){
					if(session.panier[i] != null){
						livresDuPanier += link(action:'removeItemPanier',controller:'livre',params:['targetUri': (request.forwardURI - request.contextPath), 'idItem':session.panier[i].getId()]) { '<button>X</button>' + session.panier[i].getTitre() + '<br>' }
					}
				}
			 %>
			 ${livresDuPanier}
			 <br>
			<g:link controller="reservation" action="validation"><button>Valider</button></g:link>
			<g:link params="[targetUri: (request.forwardURI - request.contextPath)]" controller="livre" action="viderPanier"><button>Vider</button></g:link>
		</div>
		<div id="grailsLogo" role="banner"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/></a></div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<r:layoutResources />
	</body>
</html>
