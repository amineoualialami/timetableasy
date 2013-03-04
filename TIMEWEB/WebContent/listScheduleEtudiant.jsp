<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
	<rich:page theme="simple" pageTitle="Planning Etudiant" markupType="html"
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
										value="#{scheduleE.model.selectedDate}" />
								</h:panelGrid>
							</t:div>
						</rich:layoutPanel>
						<rich:layoutPanel position="center">
							<t:div>

								<t:schedule value="#{scheduleE.model}" id="schedule1"
									rendered="true" visibleStartHour="6" visibleEndHour="22"
									workingEndHour="21" workingStartHour="7" readonly="false"
									theme="evolution" tooltip="false" />
							</t:div>
						</rich:layoutPanel>

					</rich:layout>
				</h:form>

	</rich:page>

</f:view>