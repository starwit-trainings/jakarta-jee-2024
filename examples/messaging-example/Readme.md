# Messaging example

## How to use

    mvn clean wildfly:dev

## Tips

How to debug Wildfly via CLI. First start CLI and connect with:

    cd target/server/bin
    ./jboss-cli.sh
    connect

Now you are connected to your running Wildfly and you can manipulate/debug container. First of all, you can use this command, to get an overview of all JNDI names:

    /subsystem=naming:jndi-view

With that view, you can construct actual JNDI lookup names.

If for some reason command execution in Wildfly plugin config didn't work, you can add a queue with this command:

    jms-queue add --queue-address=mySampleQueue --entries=java:/jms/queue/mySampleQueue

If you want to have an overview of all created queues, the following command will list them:

    /subsystem=messaging-activemq/server=default:read-children-names(child-type="jms-queue")