### Konzepte

Es wird der Payara Java EE 7 konforme Applikationsserver verwendet. Für die
Konfiguration wird möglichst immer auf das asadmin-Tool zurückgegriffen.

```bash
cd $PAYARA_HOME
bin/asadmin create-domain diguna  # templates are possible
bin/asadmin delete-domain diguna
bin/asadmin start-domain diguna
bin/asadmin stop-domain diguna

bin/asadmin deploy networkWarden.war
bin/asadmin undeploy networkWarden
# autodeploy
$PAYARA_HOME/domains/diguna/autodeploy
```

Weitere hilfreiche asadmin-Kommandos:

```bash
cd $PAYARA_HOME

# find help
bin/asadmin help list-domains
bin/asadmin help $command


# backup
bin/asadmin backup-domain \
  --backup-dir /opt/backup/ \
  $domain

# list backups
bin/asadmin list-backups $domain

#restore
bin/asadmin restore-domain \
  --backup-dir /opt/backup/ \
  $domain

# verify domain.xml
bin/asadmin verify-domain-xml

# list all domains
bin/asadmin list-domains

# list all instances
bin/asadmin list-instances

# list all applications
bin/asadmin list-applications
bin/asadmin --host xyz list-applications

# list all commands
bin/asadmin list-commands
bin/asadmin list-commands --localonly

# list timers
bin/asadmin list-timers

# uptime
bin/asadmin uptime

bin/asadmin list-jvm-options
bin/asadmin create-jvm-options -Doption=param
bin/asadmin delete-jvm-options -Doption=param
bin/asadmin generate-jvm-report
bin/asadmin list-log-levels
bin/asadmin list-loggers
bin/asadmin enable-monitoring
bin/asadmin disable-monitoring
bin/asadmin list --monitor "*"
bin/asadmin create-system-properties xyz=123
bin/asadmin list-system-properties
bin/asadmin delete-system-property
bin/asadmin version
bin/asadmin list-containers
bin/asadmin list-modules
bin/asadmin add-resources /path/to/resource.xml


bin/asadmin list "*" | grep http | grep listener
bin/asadmin get "configs.config.default-config.network-config.network-listeners.network-listener.*"
bin/asadmin set configs.config.default-config.network-config.network-listeners.network-listener.http-listener-1.enabled=true


```

#### Fachliche Strukturen und Modelle

#### Typische Muster und Strukturen

#### Persistenz

Die Daten werden in der mit dem Payara-Server mitgelieferten Derby-DB gespeichert.
Die Datenbank wird übers Netzwerk angesprochen. Ensprechend müssen im Server

- JDBC Connection Pool und
- JDBC Resource eingerichtet

werden.


- Starten der Datenbank:

```bash
cd $PAYARA_HOME
bin/asadmin start-database --dbhost localhost --dbport 1527
```

- Hilfe für das Konfigurieren: payara_diguna.sh

In der Testdatenbank wird mittels "drop-and-create" das Schema nach belieben
erzeugt. In der Produktiv-Datenbank sollten Änderungen nur über Flyway erfolgen.
Eine Hilfe beim Erstellen der DB-Scripte ist dblook. Es gibt die DDL für die
Erzeugung der Datenbank aus (natürlich erst sinnvoll nutzbar, nachdem die
Anwendung auf der TEST-DB gelaufen ist):

```bash
cd $PAYARA_HOME
javadb/bin/dblook -d 'jdbc:derby://localhost:1527/test;user=app;password=app;'
```


#### TEST-DB

Anlegen der Testdatenbank:

```bash
cd $PAYARA_HOME
bin/asadmin start-domain diguna

bin/asadmin create-jdbc-connection-pool \
  --datasourceclassname org.apache.derby.jdbc.ClientDataSource \
  --restype javax.sql.XADataSource \
  --property portNumber=1527:password=app:user=app:serverName=localhost:databaseName=test:connectionAttributes=\;create\\=true test

# test connection pool
bin/asadmin list-jdbc-connection-pools
bin/asadmin ping-connection-pool test

# create JDBC Resource
bin/asadmin create-jdbc-resource --connectionpoolid test jdbc/test
bin/asadmin list-jdbc-resources

# Deleting
bin/asadmin delete-jdbc-resource jdbc/test
bin/asadmin delete-jdbc-connection-pool test
```

- persistence.xml:

```xml
  <persistence-unit name="test" transaction-type="JTA">
    <jta-data-source>jdbc/test</jta-data-source>
    <properties>
      <property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/>
    </properties>
  </persistence-unit>
```

#### DIGUNA-DB
In der Produktiv-Datenbank



Anlegen der Produktiv-Datenbank:

```bash
bin/asadmin create-jdbc-connection-pool \
  --datasourceclassname org.apache.derby.jdbc.ClientDataSource \
  --restype javax.sql.XADataSource \
  --property portNumber=1527:password=app:user=app:serverName=localhost:databaseName=diguna:connectionAttributes=\;create\\=true diguna

bin/asadmin list-jdbc-connection-pools
bin/asadmin ping-connection-pool diguna

bin/asadmin create-jdbc-resource --connectionpoolid diguna jdbc/diguna
bin/asadmin list-jdbc-resources

# deleting
bin/asadmin delete-jdbc-resource jdbc/diguna
bin/asadmin delete-jdbc-connection-pool diguna
```

- persistence.xml:

```xml
  <persistence-unit name="diguna" transaction-type="JTA">
    <jta-data-source>jdbc/diguna</jta-data-source>
    <properties>
    </properties>
  </persistence-unit>
```

#### Flyway

Um die Datenbank immer mit dem Aktuellen Schema zu betreiben wird Flyway genutzt.
Die Klasse 

- de.cutl.diguna.networkwarden.business.databaseupdates.JeeDbMigrator

wird automatisch beim Start der Anwendung ausgeführt (@Startup Annotation) und
sorgt dafür, dass die Datenbankscripte ausgeführt werden, falls dies noch nicht
geschehen ist.
Die Scripte liegen unter:

- src/main/resources/db/migration/\*

und sind mit dem folgenden Namens-Schema benannt:

- V*${vvv}*__*${name_of_change}*.sql

Als Hilfestellung zum Erzeugen können die Definitionen für die Test-Datenbank
mittels dblook ausgelesen werden.


#### Benutzungsoberfläche

#### Ergonomie

#### Ablaufsteuerung

#### Transaktionsbehandlung

#### Sessionbehandlung

#### Sicherheit

#### Kommunikation und Integration mit anderen IT-Systemen

#### Verteilung

#### Plausibilisierung und Validierung

#### Ausnahme- / Fehlerbehandlung

#### Management des Systems & Administrierbarkeit

#### Logging, Protokollierung, Tracing

#### Geschäftsregeln

#### Konfigurierbarkeit

#### Parallelisierung und Threading

#### Internationalisierung

#### Migration

#### Testbarkeit

#### Skalierung, Clustering

JConsole für prüffung der Performance

- '''service:jmx:rmi://jndi/rmi:/localhost:8686/jmxrmi'''

#### Hochverfügbarkeit
