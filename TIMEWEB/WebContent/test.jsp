<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>
<h:form>
			<!--  The schedule itself -->
			<t:div>
			
				<t:schedule value="#{schedule.model}" id="schedule1" rendered="true" visibleStartHour="6" visibleEndHour="22"
					workingEndHour="21" workingStartHour="7" readonly="false" theme="outlookxp"
					tooltip="false"    />
			</t:div>
			<!--  The column on the left, containing the calendar and other controls -->
			<t:div>
				<h:panelGrid columns="1">
					<t:inputCalendar id="scheduleNavigator" value="#{schedule.model.selectedDate}" />
				</h:panelGrid>
				<h:commandButton actionListener="#{schedule.deleteSelectedEntry}"
					value="delete selected entry"
					rendered="#{schedule.model.entrySelected}" />

			</t:div>
		</h:form>
</f:view>
</body>
</html>