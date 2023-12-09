## Portainer Template Merger

Docker image of the Ktor app that combines templates from multiple sources into distinct templates, updated every 24 hours. And serve it.

use directly without selfhost: https://templates.h-fahmy.com/templates

Sources are:

"https://raw.githubusercontent.com/xneo1/portainer_templates/master/Template/template.json"
"https://raw.githubusercontent.com/portainer/templates/master/templates-2.0.json"
"https://raw.githubusercontent.com/dnburgess/self-hosted-template/master/template.json"
"https://raw.githubusercontent.com/Qballjos/portainer_templates/master/Template/template.json"
"https://raw.githubusercontent.com/SelfhostedPro/selfhosted_templates/portainer-2.0/Template/template.json"
"https://raw.githubusercontent.com/technorabilia/portainer-templates/main/lsio/templates/templates-2.0.json"
"https://raw.githubusercontent.com/mikestraney/portainer-templates/master/templates.json"
"https://raw.githubusercontent.com/mycroftwilde/portainer_templates/master/Template/template.json"
"https://raw.githubusercontent.com/ntv-one/portainer/main/template.json"

You can add more on your instance or make a pull request with modifications to [UpdatingJsonFile.kt](https://github.com/Hussienfahmy/PortainerAppTemplateMerger/blob/master/src/main/kotlin/com/h_fahmy/templates/UpdatingJsonFile.kt)

## Installation

### Docker Run
```
docker run -d -p 8080:8080 hussienfahmy/portainerapptemplatemerger:latest
```

### Docker Compose
``` yml
version: '3'

services:
  portainerapptemplatemerger:
    image: hussienfahmy/portainerapptemplatemerger:latest
    ports:
      - "8080:8080"
```

## Usage
In portainer settings, App Templates section 
modify the URL to http://localhost:8080/templates OR http://server-ip:8080/templates

Take care when modifying the port in run command or compose file
