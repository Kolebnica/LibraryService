# LibraryService
Service for library management.

[![Build Status](https://travis-ci.org/Kolebnica/LibraryService.svg?branch=master)](https://travis-ci.org/Kolebnica/LibraryService)

## REST API

Default port: 8083

Accessing OpenAPI: [localhost:8083/api-specs/ui/?url=http://localhost:8083/api-specs/api/openapi.json](localhost:8083/api-specs/ui/?url=http://localhost:8083/api-specs/api/openapi.json)

## Making & running a Docker image

1. Build Docker image with `docker build -t skiprope/libraryservice . `
2. Run Docker image `docker run --name skiprope-libraryservice --publish 8083:8083 --detach skiprope/libraryservice`
3. Stop & remove docker container `docker stop skiprope-libraryservice && docker rm skiprope-libraryservice`
4. Remove docker image `docker rmi skiprope/libraryservice`