# Engenharia de Software

#### Informação da Versão
PlayFramework 2.3.8

## Quickstart
Faça o clone do projeto com o github e prepare seu ambiente de trabalho com o MySQL para a base de dados e as dependências necessárias do Play Framework.

## Características

* Sistema de Gerenciamento de Compromissos
* Aplicação Web OpenSource.
* Utiliza Várias Dependências:

	* Bootstrap.
	* AngularJS.
	* Angular Bootstrap Calendar
	* Moment.js
	* ui-bootstrap


### Idiomas
* Português.

## Versões
* **0.0.1** [2015-02-24]
  * Criado o Projeto do Play em Java.
* **0.0.2** [2015-04-07]
  * Entregue o Módulo 1. - Prévia da Agenda, CRUD de Categoria de compromisso, tipo de compromisso e compromisso e documentos.

## Funções
* **Cadastros**
  * Cadastro de Categorias de Compromisso - **Ok**
  * Cadastro de Tipo de Compromisso - **Ok**
  * Cadastro de Compromissos - **Ok**
  * Cadastro de Contatos - **Ok**
  * Cadastro de Usuários - **Ok**

* **Validações**
  * Não Cadastrar Compromisso sem nome ou nomes iguais - **OK**
  * Não Cadastrar Categoria de Compromisso sem nome ou nomes iguais - **OK**
  * Não Cadastrar Tipo de Compromisso sem nome ou nomes iguais- **OK**
  * Não Contato sem nome ou nomes iguais- **OK**
  * Não Usuario sem email e sem senha ou usuario com nomes iguais- **OK**
  
## Links

* [Play Framework](https://www.playframework.com/)
* [AngularJS](https://angularjs.org/)
* [Bootstrap](http://getbootstrap.com/)
* [Jquery](http://jquery.com/)
* [Bower](http://bower.io/)
* [MySQL](http://www.mysql.com/)
* [Java8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Documentação

Há uma única directiva para criar o calendário, use-a da seguinte forma:
```javascript
<mwl-calendar
    events="events"
    view="calendarView"
    view-title="calendarTitle"
    current-day="calendarDay"
    on-event-click="eventClicked(calendarEvent)"
    edit-event-html="'<i class=\'glyphicon glyphicon-pencil\'></i>'"
    delete-event-html="'<i class=\'glyphicon glyphicon-remove\'></i>'"
    on-edit-event-click="eventEdited(calendarEvent)"
    on-delete-event-click="eventDeleted(calendarEvent)"
    auto-open="true">
</mwl-calendar>
```
### eventos

Uma lista de eventos para exibir no calendário. Por exemplo:
```javascript
$scope.events = [
  {
    title: 'My event title', // O título do evento
    type: 'info', // Este é o tipo do evento (determinado pela cor). Pode ser importante: warning, info, inverse, success or special
    startsAt: new Date(2013,5,1,1), // Data Início - Um javascript date object para quando o evento iniciar
    endsAt: new Date(2014,8,26,15), // Data termino - Um javascript date object para quando o evento terminar
    editable: false, // If edit-event-html is set and this field is explicitly set to false then dont make it editable
    deletable: false, // If delete-event-html is set and this field is explicitly set to false then dont make it deleteable
    incrementsBadgeTotal: true //If set to false then will not count towards the badge total amount on the month and year view
  }
];
```

## Informações Adicionais
* [Centro Universitário Dinâmica das Cataratas](http://www.udc.edu.br/v3/udc/) - Trabalho para a conclusão parcial do curso de Sistemas de Informação. Foz do Iguaçu - Paraná - Brasil.

## License

Copyright (c) 2014-2015 Haroldo Ramirez da Nóbrega
