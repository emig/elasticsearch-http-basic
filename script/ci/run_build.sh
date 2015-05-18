#! /usr/bin/env sh
mvn -Delasticsearch.version=$ES_VERSION -Dtests.security.manager=false
exit 0
