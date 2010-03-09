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
<style type="text/css">
	body{
		color:darkblue;
	}
	input{
		background:none;
	}
</style>
</head>
<body>
<f:view>
	<h:form>
		<table>
			<tr>
				<td>
					<img src="../../Imagens/logo_menor.png"/>
				</td>
				<td>
					<h1>Sistema de Gerenciamento Imobiliário</h1>
				</td>
			</tr>
		</table>
		<rich:toolBar>
			
			<rich:dropDownMenu value="Inquilino">
				<rich:menuItem value="Pesquisar Imóveis"/>
				<rich:menuItem value="Verificar Débitos" />
			</rich:dropDownMenu>
			<rich:dropDownMenu value="Proprietário">
				<rich:menuItem value="Cadastrar Imóvel"/>
				<rich:menuItem value="Listar Seus Imóveis" />
			</rich:dropDownMenu>
			<rich:dropDownMenu value="Administrador">
				<rich:menuItem value="Cadastrar Inquilino"/>
				<rich:menuItem value="Cadastrar Proprietário" />
				<rich:menuItem value="Cadastrar Imóvel" />
			</rich:dropDownMenu>
		</rich:toolBar>
		<rich:tabPanel>
			<h:messages />
			<h:panelGrid columns="2">
				<h:outputText value="Nome"/>
				<h:inputText value="#{proprietarioBean.proprietario.nome}"/>
				<h:outputText value="Data de Nascimento"/>
				<rich:calendar value="#{proprietarioBean.proprietario.dataNascimento}"/>
				<h:outputText value="Endereço"/>
				<h:inputText value="#{proprietarioBean.proprietario.endereco}"/>
				<h:outputText value="Telefone"/>
				<h:inputText value="#{proprietarioBean.proprietario.telefone}"/>
				<h:commandButton value="Cadastrar" action="#{proprietarioBean.incluir}"/>
			</h:panelGrid>
		</rich:tabPanel>
	</h:form>
</f:view>
</body>
</html>