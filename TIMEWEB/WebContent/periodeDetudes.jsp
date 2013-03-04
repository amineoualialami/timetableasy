<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
	<rich:page theme="simple" pageTitle="Periode d'etude" markupType="html"
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



		<style>
.label {
	font-weight: bold;
}

.pbody {
	width: 200px;
}
</style>



		<rich:tabPanel switchType="client">
			<rich:tab label="Liste des Periodes d'etudes">
				<a4j:form>



					<h:panelGrid columns="3">
						<h:outputText value="Cursus :" />
						<h:selectOneMenu id="list" value="#{periodeDetude.cursusSelected}">
							<f:selectItems value="#{periodeDetude.listCursusSL}"></f:selectItems>
						</h:selectOneMenu>
						<a4j:commandButton value="OK" action="#{periodeDetude.test}"
							reRender="ListPeriodeDetudes" />
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
						<rich:menuItem ajaxSingle="true">
							<b>{nom} </b>
							<a4j:actionparam name="det" assignTo="#{ddmenu.current}"
								value="{nom}" />
						</rich:menuItem>
						<rich:menuGroup value="Actions">
							<rich:menuItem action="#{periodeDetude.removePeriodeDetude}"
								value="Supprimer" ajaxSingle="true"
								reRender="ListPeriodeDetudes">
								<a4j:actionparam name="baskk" assignTo="#{periodeDetude.nom}"
									value="{nom}" />
							</rich:menuItem>

							<rich:menuItem action="#{periodeDetude.detailsPeriodeDetude}"
								value="Details" ajaxSingle="true" reRender="ListPeriodeDetudes">
								<a4j:actionparam name="baskk" assignTo="#{periodeDetude.nom}"
									value="{nom}" />
							</rich:menuItem>

							<rich:menuItem action="#{periodeDetude.updatePeriodeDetude}"
								value="Modifier" ajaxSingle="true" reRender="ListPeriodeDetudes">
								<a4j:actionparam name="baskk" assignTo="#{periodeDetude.nom}"
									value="{nom}" />
							</rich:menuItem>
						</rich:menuGroup>
					</rich:contextMenu>


					<rich:dataTable id="ListPeriodeDetudes" rows="10"
						value="#{periodeDetude.listPeriodeDetudes}" var="periodeDetude"
						onRowMouseOver="this.style.backgroundColor='#5698a6'"
						onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
						<f:facet name="header">
							<rich:columnGroup>

								<h:column>
									<h:outputText value="Nom" />
								</h:column>
								<h:column>
									<h:outputText value="Date Début" />
								</h:column>
								<h:column>
									<h:outputText value="Date Fin" />
								</h:column>


							</rich:columnGroup>
						</f:facet>
						<h:column>
							<h:outputText value="#{periodeDetude.nom}" />
						</h:column>
						<h:column>
							<rich:calendar value="#{periodeDetude.dateDebut}" readonly="true"></rich:calendar>
						</h:column>
						<h:column>
							<rich:calendar value="#{periodeDetude.dateFin}" readonly="true"></rich:calendar>
						</h:column>


						<f:facet name="footer">
							<rich:datascroller></rich:datascroller>
						</f:facet>

						<rich:componentControl event="onRowClick" for="menu"
							operation="show">
							<f:param value="#{periodeDetude.nom}" name="nom" />
						</rich:componentControl>

					</rich:dataTable>

				</h:form>

			</rich:tab>


			<rich:tab label="Ajouter une periode d'etude">

				<a4j:form>

					<h:panelGrid columns="2">

						<h:outputText value="Cursus :" />
						<h:selectOneMenu id="list" value="#{periodeDetude.cursusSelected}">
							<f:selectItems value="#{periodeDetude.listCursusSL}"></f:selectItems>
						</h:selectOneMenu>

						<h:outputText value="Nom :" />
						<h:inputText value="#{periodeDetude.nom}" id="nom" />


						<h:outputText value="Date de Début :" />
						<rich:calendar value="#{periodeDetude.dateDebut}"></rich:calendar>

						<h:outputText value="Date de Fin :" />
						<rich:calendar value="#{periodeDetude.dateFin}"></rich:calendar>

						<h:outputText value="Cours :" />
						<rich:pickList value="#{periodeDetude.courSL}">
							<f:selectItems value="#{periodeDetude.allCoursSL}" />
						</rich:pickList>

						<f:facet name="footer">
							<a4j:commandButton value="OK"
								action="#{periodeDetude.persistPeriodeDetude}" />
						</f:facet>
					</h:panelGrid>

				</a4j:form>
			</rich:tab>
		</rich:tabPanel>




	</rich:page>



</f:view>