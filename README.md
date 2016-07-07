## sample-kinesis-connector

A sample application that consumes from Twitter enterprise streams using HBC and produces messages into Amazon Kinesis

## Requirements
* Java 1.7
* Maven

## Getting Started
This is an example app that takes in a Gnip Power Track and streams it into [AWS Kinesis](http://aws.amazon.com/kinesis/). This application can be deployed as is with a few edits to the configuration file:

  1. Clone the project: ```git clone https://github.com/twitterdev/sample-kinesis-connector sample-kinesis-connector && cd ~/sample-kinesis-connector```
  2. Create a config.properties file in src/main/resources: ```cd src/main/resources && mv config.properties.example config.properties```
  3. Edit the newly created file to contain your information: ```vim config.properteis```
  </br>
  Should look similar to this:
  ```bash
    gnip.account.name=YOUR_GNIP_ACCOUNT_NAME
    gnip.product=YOUR_GNIP_PRODUCT
    gnip.stream.label=YOUR_GNIP_STREAM_LABEL


    #Application configuration parameters-
    ########################
    #Do not change these settings
    ########################
    producer.thread.count=95
    batch.size=0
    aws.kinesis.shard.count=2
    message.queue.size=300
    bytes.queue.size=3000
    gnip.client.id=1
    rate.limit=-1
    metric.report.interval.seconds=60
  ```
  4. Build the project with docker: ```docker build -t meltwater/gnip_consumer .```
  5. Run the project with: ```docker run -it -e GNIP_USER_NAME= -e GNIP_USER_PASSWORD= -e AWS_ACCESS_KEY_ID= -e AWS_SECRET_ACCESS_KEY= -e AWS_KINESIS_STREAM_NAME= --name gnip_consumer meltwater/gnip_consumer```





## Notes
This sample has been tested on [Digital Ocean](https://www.digitalocean.com/) box with 1 Gb RAM. The ability for the application to produce to Kinesis is very sensitive to the quality the network connection. In testing, we were able to get a Gnip Decahose to flow quite well, only hitting the Kinesis rate limit a few times, all of which were recoverable errors.


