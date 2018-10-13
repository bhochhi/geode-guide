#!/usr/bin/env zsh

# referred from https://github.com/lshannon/geode-aws-deployment-scripts/blob/master/local_scripts/
#######################################################################
# Runs locally to set up environment to use `gfsh` to run locally
#######################################################################
cd $( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

if [ -n "$JAVA_HOME" ]; then
  export HOST=localhost
  export SERVER_DIR_LOCATION=./geode
  export LOCATOR="locator";
  export LOCATOR_IP=127.0.0.1
  export LOCATOR_PORT=10334
  export SERVER_HOST=$HOST
else
	echo "This requires the Java JDK and JAVA_HOME to be set"
	echo "Geode currently supports: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html"
	echo "If you already have the JDK, trying running 'export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)' and then running this script."
	echo "Press any key to terminate this script"
	read anykey
	exit 1
fi
