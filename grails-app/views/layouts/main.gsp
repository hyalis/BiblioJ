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
				padding: 10px;
				float : right;
				width : 200px;
				height : auto;
				text-align : left;
				background-color: #4B8DB3;
				border-radius: 5px;
				color: white;
				font-size: 15px;
			}
			#panier a{
				color : red;
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
				for(int i = 0; session.panier != null && i<session.panier?.size(); i++){
					if(session.panier[i] != null){
						livresDuPanier += link(action:'removeItemPanier',controller:'livre',params:['targetUri': (request.forwardURI - request.contextPath), 'idItem':session.panier[i].getId()]) { 'X' }
						livresDuPanier += '  ' + session.panier[i].getTitre() + '<br>'
					}
				}
			 %>
			 ${livresDuPanier}
			 <br>
			 <%
				String btn = ''
				if(session.panier?.size()>0){
					btn += link(action:'rapport',controller:'reservation') { '<button>Valider</button>    ' }
					btn += link(action:'viderPanier',controller:'livre', params:[targetUri: (request.forwardURI - request.contextPath)]) { '<button>Vider</button>' }
				} else {
					btn = 'Panier vide'
				}
			%>
			${btn}
		</div>
		<div id="grailsLogo" role="banner"><a href="/BiblioJ"><img src="http://www.tunandroid.com/content/wp-content/uploads/2011/03/GoogleBooksLogo.png"  alt="Grails" id="logoBiblio"/> <h1>BiblioJ</h1></a></div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<r:layoutResources />
	</body>
</html>
