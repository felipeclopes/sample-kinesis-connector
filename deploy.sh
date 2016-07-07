#!/bin/bash
LOGIN_STRING=`aws ecr get-login --region us-east-1`
${LOGIN_STRING}
docker build -t meltwater/gnip_consumer .
docker tag meltwater/gnip_consumer 421268985564.dkr.ecr.us-east-1.amazonaws.com/meltwater/gnip_consumer:latest
docker push 421268985564.dkr.ecr.us-east-1.amazonaws.com/meltwater/gnip_consumer:latest

