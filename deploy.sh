#!/usr/bin/env bash

mvn clean package

cp target/contagem-palavras.war $WILDFLY_HOME/standalone/deployments/

echo ">> Deploy disparado! Verifique o log do WildFly para concluir."
