<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
	<rich:page theme="simple" pageTitle="Campus" markupType="html"
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
			<rich:tab label="Liste des Campus">



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
							<rich:menuItem action="#{campus.removeCampus}" value="Supprimer"
								ajaxSingle="true" reRender="ListCampus">
								<a4j:actionparam name="baskk" assignTo="#{campus.nom}"
									value="{nom}" />
							</rich:menuItem>
							<rich:menuItem action="#{campus.updateCampus}" value="Modifier"
								ajaxSingle="true">
								<a4j:actionparam name="bask" assignTo="#{campus.nom}"
									value="{nom}" />
							</rich:menuItem>
						</rich:menuGroup>
					</rich:contextMenu>


					<rich:dataTable id="ListCampus" rows="10"
						value="#{campus.allCampus}" var="campus"
						onRowMouseOver="this.style.backgroundColor='#5698a6'"
						onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
						<f:facet name="header">
							<rich:columnGroup>

								<h:column>
									<h:outputText value="Nom" />
								</h:column>

								<h:column>
									<h:outputText value="Responsables" />
								</h:column>
							</rich:columnGroup>
						</f:facet>
						<h:column>
							<h:outputText value="#{campus.nom}" />
						</h:column>

						<h:column>
							<h:dataTable value="#{campus.users}" var="user">
								<h:column>
									<h:outputText value="#{user.nom}" />
								</h:column>
							</h:dataTable>
						</h:column>





						<f:facet name="footer">
							<rich:datascroller></rich:datascroller>
						</f:facet>
						<rich:componentControl event="onRowClick" for="menu"
							operation="show">
							<f:param value="#{campus.nom}" name="nom" />
						</rich:componentControl>
					</rich:dataTable>

				</h:form>

			</rich:tab>
			<rich:tab label="Ajouter un nouveau Campus">
				<a4j:form>

					<h:panelGrid columns="2">

						<h:outputText value="Nom :" />
						<h:inputText value="#{campus.nom}" id="nom" />

						<h:outputText value="Responsables :" />
						<rich:pickList value="#{campus.responsableSL}">
							<f:selectItems value="#{campus.allResponsablesSL}" />
						</rich:pickList>


						<f:facet name="footer">
							<a4j:commandButton value="OK" action="#{campus.persistCampus}" />
						</f:facet>
					</h:panelGrid>

				</a4j:form>
			</rich:tab>
		</rich:tabPanel>




	</rich:page>



</f:view>