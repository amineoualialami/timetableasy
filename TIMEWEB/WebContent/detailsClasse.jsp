<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
	<rich:page theme="simple" pageTitle="Details Classe" markupType="html"
		sidebarPosition="left" sidebarWidth="250">

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
			<rich:panel header="Details Classe">
				<h:panelGrid columns="2">

						<h:outputText value="Campus :" />
						<h:inputText value="#{classe.campus1.nom}" readonly="true"></h:inputText>

						<h:outputText value="Cursus :" />
						<h:inputText value="#{classe.cursus1.nom}" readonly="true"></h:inputText>

						<h:outputText value="Nom :" />
						<h:inputText value="#{classe.nom}" id="nom" readonly="true" />


						<h:outputText value="Etudiants inscrits :" />
						<rich:panel>
							<h:dataTable value="#{classe.etudiantsActuel}" var="etudiant">
								<h:column>
									<h:outputText value="#{etudiant.nom}" />
								</h:column>
							</h:dataTable>
						</rich:panel>


						<f:facet name="footer">
							<a4j:commandButton value="return" action="#{classe.listClasses}" />
						</f:facet>
					</h:panelGrid>
			</rich:panel>
		</a4j:form>




	</rich:page>



</f:view>