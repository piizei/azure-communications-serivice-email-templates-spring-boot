# azure-communications-serivice-email-templates-spring-boot
Using email templates in azure communications service with spring boot

* Configure Azure Communications Service connection-string into application.properties

The system expects templates to be on the resources folder.

Example request to invoke it (POST http://localhost:8080/email/send):
```json
{
  "template": "template1.html",
  "to": "recpieint@emailaddress.com",
  "subject": "test email",
  "sender": "DoNotReply@my-emaildomain.net",
  "templateData": {
    "headertext": "<p>This is marketing email header example</p>",
    "buttonlink": "http://portal.azure.com",
    "buttontext": "Azure Portal",
    "bodytext": "<p> Clicking the button you can get into Azure portal</p>"
  }
}
```


## Templates

The system has 2 levels of template, a common wrapper and one that can be given as input.
In the example message, 'template1.html' is given as input and is inserted inside of main.html (the wrapper).

The system uses {{handlebars}} for replacement fields, but you can edit the replacement function to use something else.


## Authentication

### Connection string
Specify azure.communication.connection-string in application.properties.

### Managed identity
Leave azure.communication.connection-string empty or remove it from application.properties.
Add azure.communication.endpoint in application.properties.