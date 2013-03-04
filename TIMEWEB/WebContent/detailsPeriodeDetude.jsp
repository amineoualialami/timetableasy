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
			<rich:panel header="Details Periode D'etude">
				<a4j:form>

					<h:panelGrid columns="2">



						<h:outputText value="Nom :" />
						<h:inputText value="#{periodeDetude.nom}" id="nom" />


						<h:outputText value="Date de Début :" />
						<rich:calendar value="#{periodeDetude.dateDebut}"></rich:calendar>

						<h:outputText value="Date de Fin :" />
						<rich:calendar value="#{periodeDetude.dateFin}"></rich:calendar>


						<h:outputText value="Cours :" />
						<rich:panel>
							<h:dataTable value="#{periodeDetude.coursActuel}" var="cour">
								<h:column>
									<h:outputText value="#{cour.nom}" />
								</h:column>
							</h:dataTable>
						</rich:panel>



						<f:facet name="footer">
							<a4j:commandButton value="Return"
								action="#{periodeDetude.listPeriodeDetude}" />
						</f:facet>
					</h:panelGrid>

				</a4j:form>
			</rich:panel>
		</a4j:form>




	</rich:page>



</f:view>