<%@ page import="biblioj.Auteur" %>



<div class="fieldcontain ${hasErrors(bean: auteurInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="auteur.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${auteurInstance?.nom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auteurInstance, field: 'prenom', 'error')} required">
	<label for="prenom">
		<g:message code="auteur.prenom.label" default="Prenom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="prenom" required="" value="${auteurInstance?.prenom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: auteurInstance, field: 'livres', 'error')} ">
	<label for="livres">
		<g:message code="auteur.livres.label" default="Livres" />
		
	</label>
	
</div>

