#!/usr/local/bin/zsh
# referred from https://github.com/lshannon/geode-aws-deployment-scripts/blob/master/local_scripts/
#######################################################################
# Runs locally to Stop and clean up a locally running Geode cluster
#######################################################################
cd $( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source set_env.sh
echo "Cleaning Up Working Folders"
rm -fr ./locator
rm -fr ./serverA
rm -fr ./serverB
