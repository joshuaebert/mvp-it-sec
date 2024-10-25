## Apache Configuration
Add the following to your default site (/etc/apache2/sites-enabled/000-default.conf):

```
ProxyPreserveHost On
ProxyPass /ktor http://localhost:8080/ 
ProxyPassReverse /ktor http://localhost:8080/
```

Ensure that both **proxy** and **http_proxy** modules are running
```
sudo a2enmod proxy
sudo a2enmod proxy_http
```

Restart the Webserver
```
systemctl restart apache2
```

## Compiling & Running
I used **corretto 11**. Ensure that your JDK/JRE Version is the same or newer
