<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
	<rich:page theme="simple" pageTitle="Planning" markupType="html"
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





		<rich:panel  header="Ajouter un evenement" >

				<a4j:form>
					<h:panelGrid columns="2">
						<h:outputText value="Campus :" />
						<h:selectOneMenu id="listCampus"
							value="#{schedule.campusSelected}" onchange="this.form.submit();">
							<f:selectItems value="#{schedule.listCampusSL}"  ></f:selectItems>
						</h:selectOneMenu>
						


						<h:outputText value="Classe :" />
						<h:selectOneMenu id="listClasse"
							value="#{schedule.classeSelected}" onchange="this.form.submit();">
							<f:selectItems value="#{schedule.listClasseSL}"></f:selectItems>
						</h:selectOneMenu>
						



						<h:outputText value="Cursus :" />
						<h:inputText id="listCursus" value="#{schedule.cursusSelected}"
							readonly="true" />
						

						<h:outputText value="Periode d'étude :" />
						<h:selectOneMenu id="listPD" value="#{schedule.pdSelected}" onchange="this.form.submit();" >
							<f:selectItems value="#{schedule.listPdSL}"></f:selectItems>
						</h:selectOneMenu>
						

						<h:outputText value="Cours :" />
						<h:selectOneMenu id="listCours" value="#{schedule.courSelected}"onchange="this.form.submit();" >
							<f:selectItems value="#{schedule.listCoursSL}"></f:selectItems>
						</h:selectOneMenu>
						

					</h:panelGrid>
				</a4j:form>

				<style>
.label {
	font-weight: bold;
}

.pbody {
	width: 200px;
}
</style>
				<h:form id="form">
					<rich:contextMenu attached="false" id="menu" submitMode="ajax">

						<rich:menuItem action="#{schedule.test6}" value="Select"
							ajaxSingle="true" reRender="ListEspaces">
							<a4j:actionparam name="baskk"
								assignTo="#{schedule.modaliteSelected}" value="{nom}" />
						</rich:menuItem>

					</rich:contextMenu>

					<rich:dataTable id="listModalite" rows="3"
						value="#{schedule.listModalitesP}" var="modalite"
						onRowMouseOver="this.style.backgroundColor='#5698a6'"
						onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
						<f:facet name="header">
							<rich:columnGroup>
								<h:column>
									<h:outputText value="Nom" />
								</h:column>
								<h:column>
									<h:outputText value="Horraire Modalite" />
								</h:column>
							</rich:columnGroup>
						</f:facet>
						<h:column>
							<h:outputText value="#{modalite.modalite.nom}" />
						</h:column>
						<h:column>
							<h:outputText value="#{modalite.horraireModalite}" />
						</h:column>

						<rich:componentControl event="onRowClick" for="menu"
							operation="show">
							<f:param value="#{modalite.modalite.nom}" name="nom" />
						</rich:componentControl>
					</rich:dataTable>
				</h:form>

                <rich:dataTable id="listMP" rows="3"
						value="#{schedule.listMP}" var="modalite"
						onRowMouseOver="this.style.backgroundColor='#5698a6'"
						onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
						<f:facet name="header">
							<rich:columnGroup>
								<h:column>
									<h:outputText value="Nom" />
								</h:column>
								<h:column>
									<h:outputText value="Horraire Planifié" />
								</h:column>
							</rich:columnGroup>
						</f:facet>
						<h:column>
							<h:outputText value="#{modalite.modalite.nom}" />
						</h:column>
						<h:column>
							<h:outputText value="#{modalite.horraireModalite}" />
						</h:column>
					</rich:dataTable>

				<a4j:form>
					<h:panelGrid columns="3">

						<h:outputLabel for="from" value="from:" />
						<t:inputDate id="from" value="#{schedule.from}" type="both" onchange="this.form.submit();"
							popupCalendar="true" />
						<h:message for="from" />
						
						<h:outputLabel for="until" value="until:" />
						<t:inputDate id="until" value="#{schedule.until}" type="both" onchange="this.form.submit();"
							popupCalendar="true" />
						<h:message for="until" />

						<h:outputText value="Intervenants :" />
						<h:selectOneMenu id="listIntervenants"
							value="#{schedule.intervenantSelected}" onchange="this.form.submit();" >
							<f:selectItems value="#{schedule.listIntervenantsSL}"></f:selectItems>
						</h:selectOneMenu>
						<h:message for="listIntervenants" />


						<h:outputText value="Espaces :" />
						<h:selectOneMenu id="listEspaces"
							value="#{schedule.espaceSelected}" onchange="this.form.submit();" >
							<f:selectItems value="#{schedule.listEspacesSL}"></f:selectItems>
						</h:selectOneMenu>
						<h:message for="listEspaces" />

						<f:facet name="footer">
							<a4j:commandButton value="OK" action="#{schedule.addEntry}" />
						</f:facet>
					</h:panelGrid>

				</a4j:form>

	</rich:panel>
			




	</rich:page>



</f:view>