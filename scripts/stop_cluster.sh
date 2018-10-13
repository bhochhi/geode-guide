#!/usr/bin/env ksh
# referred from https://github.com/lshannon/geode-aws-deployment-scripts/blob/master/local_scripts/
##############################################################################
# Runs locally using `gfsh` to stop all Locally running Geode processes
##############################################################################
cd $( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source ./set_env.sh
gfsh -e "connect" -e "shutdown --include-locators=true"
echo "The cluster including the Locators is shut down. Press any key to exit..."