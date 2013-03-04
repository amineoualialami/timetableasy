<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
<rich:page theme="simple" pageTitle="coco" markupType="html" sidebarPosition="left" sidebarWidth="250" >

    <f:facet name="header" >

      <h:graphicImage  url="images/globusuniversity.png"></h:graphicImage>
    </f:facet>

    <f:facet name="sidebar">
        <rich:panelMenu mode="ajax" >
            <rich:panelMenuGroup label="Administration" action="#{bean.action}"> 
                <rich:panelMenuItem label="Utilisateurs" reRender="test" action="#{user.listUtilisateurs}"> 
                        <f:param value="test value" name="test"/> 
                </rich:panelMenuItem>
                <rich:panelMenuItem label="Classes" reRender="test" action="#{espace.listEspaces}"> 
                        <f:param value="test value" name="test"/> 
                </rich:panelMenuItem> 
                <rich:panelMenuItem label="Cursus" reRender="test" action="#{espace.listEspaces}"> 
                        <f:param value="test value" name="test"/> 
                </rich:panelMenuItem>
                <rich:panelMenuItem label="Campus" reRender="test" action="#{espace.listEspaces}"> 
                        <f:param value="test value" name="test"/> 
                </rich:panelMenuItem>
             </rich:panelMenuGroup>
             <rich:panelMenuGroup label="Test" action="#{bean.action}" expanded="true" disabled="#{user.bool}"> 
                <rich:panelMenuItem label="test 1" reRender="test" action="#{espace.listEspaces}"> 
                        <f:param value="test value" name="test"/> 
                </rich:panelMenuItem> 
                <rich:panelMenuItem label="test 2" reRender="test" action="#{espace.listEspaces}"> 
                        <f:param value="test value" name="test"/> 
                </rich:panelMenuItem>
             </rich:panelMenuGroup>
         </rich:panelMenu>

    </f:facet>

    <f:facet name="footer">
         <h:outputText value="Footer" />
        <!-- footer  content -->

    </f:facet>
    
    <rich:layout>

        <rich:layoutPanel position="top">

                <!--top-->

        </rich:layoutPanel>

        <rich:layoutPanel position="left">

                <!--left-->

        </rich:layoutPanel>

        <rich:layoutPanel position="center">
                 <br/>
                 <br/>
                 <br/>
                <!--center-->
                 

        </rich:layoutPanel>

        <rich:layoutPanel position="right">

                <!--right-->

        </rich:layoutPanel>

        <rich:layoutPanel position="bottom">

                <!--bottom-->

        </rich:layoutPanel>

</rich:layout>
     
                  

</rich:page>


    
</f:view>