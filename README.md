# db-api Microservice
* RESTful API Application to extract data from Oracle database in Json form

## API Endpoints:
* /data: to fetch data object's contents
* /meta: to fetch meta data of object

## Parameters:
* objName: Object Name (table/view) needs to fetch. Use with schema name if not in the current schema.
* filters:<br>
&nbsp;- where condition to customize your query. E.g: "*filters=WHERE ROWNUM<5*" <br>
&nbsp;- only applicable for **/data** Endpoint<br>

## Set up:
* Create **app.properties** file in the same format as **app_sample.properties** and provide database information for connection.
