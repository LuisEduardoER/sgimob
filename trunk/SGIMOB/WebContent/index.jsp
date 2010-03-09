<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>
	<h:form>
		<rich:panel>
			<h:panelGrid>
				<h1>Cadastro de Inquilino</h1>
				<rich:toolBar>
					<rich:dropDownMenu value="Inquilino">
						<rich:menuItem value="Cadastrar" action="cadastrarInquilino" />
					</rich:dropDownMenu>
				</rich:toolBar>
				<h:outputText value="Nome do Inquilino"/>
				<h:inputText value="#{}"/>
				<h:outputText value="Data de Nasciemtno do Inquilino"/>
				<h:inputText value="#{inquilinoBean.inquilino.data_nascimento}">
					<f:convertDateTime pattern="dd/MM/yyyy" locale="pt_BR"/>
				</h:inputText>
				<h:outputText value="Endereço do Inquilino"/>
				<h:inputText value="#{inquilinoBean.inquilino.endereco}"/>
				<h:outputText value="Telefone do Inquilino"/>
				<h:inputText value="#{inquilinoBean.inquilino.telefone}"/>
				<h:selectOneMenu value="#{inquilinoBean.inquilino.id}">
					<f:selectItems value="#{inquilinoBean.listaItems}" />
				</h:selectOneMenu>
				<h:commandButton value="Teste" action="#{inquilinoBean.incluir}"/>
			</h:panelGrid>	
		</rich:panel>
	</h:form>
</f:view>
</body>
</html>