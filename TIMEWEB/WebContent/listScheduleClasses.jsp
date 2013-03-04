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









		<h:form>
			<rich:layout>

				<rich:layoutPanel position="left">
					<t:div>
						<h:panelGrid columns="1">
							<t:inputCalendar id="scheduleNavigator"
								value="#{schedule.model.selectedDate}" />
						</h:panelGrid>
						<h:commandButton actionListener="#{schedule.deleteSelectedEntry}"
							value="delete selected entry"
							rendered="#{schedule.model.entrySelected}" action="listSchedule" />
						<a4j:form>
							<h:panelGrid columns="2">
								<h:outputText value="Campus :" />
								<h:selectOneMenu id="listCampus"
									value="#{schedule.campusSelected}"
									onchange="this.form.submit();">
									<f:selectItems value="#{schedule.listCampusSL}"></f:selectItems>
								</h:selectOneMenu>

								<h:outputText value="Classe :" />
								<h:selectOneMenu id="listClasse"
									value="#{schedule.classeSelected}"
									onchange="this.form.submit();">
									<f:selectItems value="#{schedule.listClasseSL}"></f:selectItems>
								</h:selectOneMenu>
							</h:panelGrid>
							<a4j:commandButton value="OK" action="#{schedule.listSchedule}" reRender="schedule1" ></a4j:commandButton>
						</a4j:form>
						
					</t:div>
				</rich:layoutPanel>
				<rich:layoutPanel position="center">
					<t:div>

						<t:schedule value="#{schedule.model}" id="schedule1"
							rendered="true" visibleStartHour="6" visibleEndHour="22"
							workingEndHour="21" workingStartHour="7" readonly="false"
							theme="evolution" tooltip="false" />
					</t:div>
				</rich:layoutPanel>


			</rich:layout>
		</h:form>







	</rich:page>



</f:view>