## CockroachDB with r2dbc
This is an example to demonstrate connecting Java code running r2dbc to a CockroachDB database using the r2dbc-postgresql library.

To create this, I followed the example from this page: https://hantsy.github.io/spring-reactive-sample/data/data-r2dbc.html

A few things I noticed when running this:
- Java was really strict about me using a self-signed cert when I was running CockroachDB locally. I converted my self-signed CA cert to DER format to make it work (I always have to do this in Java projects); but in this case, I had to also jump through some hoops to make Java accept my self-signed cert:
```bash
sudo keytool -importcert -file /Users/jimhatcher/local_certs/ca.crt -keystore /Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/lib/security/cacerts -alias local_crdb_root_cert
```
- I couldn't get certs-based authentication to work.  When I supplied the ssl key/cert combo (which should be enough for cert-based auth), the r2dbc driver would tell me I need to supply the username/password.  So, I just used username/password authentication.