<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
	<rich:page theme="simple" pageTitle="Details Cour" markupType="html"
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
			<rich:panel header="Details Cour">
				<h:panelGrid columns="2">

					<h:outputText value="Nom :" />
					<h:inputText value="#{cour.nom}" id="nom" readonly="true"/>

					<rich:separator lineType="beveled" height="8" width="80%"
						align="center" />
					<br />
					<br />

					<h:outputText value="#{cour.modalite1.nom}" />
					<h:inputText value="#{cour.horraireModalite1}" readonly="true" />

					<h:outputText value="#{cour.modalite2.nom}" />
					<h:inputText value="#{cour.horraireModalite2}" readonly="true" />

					<h:outputText value="#{cour.modalite3.nom}" />
					<h:inputText value="#{cour.horraireModalite3}" readonly="true" />

					<f:facet name="footer">
						<a4j:commandButton value="Return" action="#{cour.listCours}" />
					</f:facet>
				</h:panelGrid>
			</rich:panel>
		</a4j:form>




	</rich:page>



</f:view>