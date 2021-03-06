<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
	<rich:page theme="simple" pageTitle="Modifier un Utilisateur"
		markupType="html" sidebarPosition="left" sidebarWidth="250">

		<f:facet name="header">
			<%@ include file="layout/header.jspf"%>
		</f:facet>

		<f:facet name="sidebar">
			<%@ include file="layout/sidebar.jspf"%>
		</f:facet>

		<f:facet name="footer">
			<%@ include file="layout/footer.jspf"%>
		</f:facet>
		
		
		
		<a4j:form>
			<rich:panel header="Modifier un Utilisateur">
				<h:panelGrid columns="2">

					<h:outputText value="Nom :" />
					<h:inputText value="#{user.nom}" id="nom" readonly="true" />
					<h:outputText value="Prenom :" />
					<h:inputText value="#{user.prenom}" id="prenom" />

					<h:outputText value="Login :" />
					<h:inputText value="#{user.login}" id="login" />
					<h:outputText value="Password :" />
					<h:inputText value="#{user.password}" id="password" />

					<h:outputText value="Roles Actuels :" />
					<rich:panel>
						<h:dataTable value="#{user.anciensRoles}" var="role">
							<h:column>
								<h:outputText value="#{role.titreRole}" />
							</h:column>
						</h:dataTable>
					</rich:panel>
					<h:outputText value="Nouveaux Roles :" />
					<rich:pickList value="#{user.newRolesSL}">
						<f:selectItems value="#{user.allrolesSL}" />
					</rich:pickList>


					<f:facet name="footer">
						<a4j:commandButton value="OK" action="#{user.mergeUser}" />
					</f:facet>
				</h:panelGrid>
			</rich:panel>
		</a4j:form>




	</rich:page>



</f:view>