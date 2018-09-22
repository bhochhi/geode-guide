Geode Running in docker container
---
    - docker run -it -p 10334:10334 -p 7575:7575 -p 1099:1099 -p 40404:40404 apachegeode/geode gfsh
    - gfsh> start locator
    - gfsh> start server
    - gfsh> create region --name=hello --type=REPLICATE 
    
