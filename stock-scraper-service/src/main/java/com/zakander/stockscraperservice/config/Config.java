package com.zakander.stockscraperservice.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
@EnableDynamoDBRepositories
public class Config {	
	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(amazonDynamoDB());
	}
	
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		String accessKey = "AKIAVP234CBC47SNRSVP";
		String secretAccessKey = "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";
		return AmazonDynamoDBClientBuilder
				.standard()
				.withEndpointConfiguration(
						new EndpointConfiguration(
								"http://localhost:8001/",
								"us-east-1"
						)
				)
				.withCredentials(
						new AWSStaticCredentialsProvider(
								new BasicAWSCredentials(
										accessKey,
										secretAccessKey
								)
						)
				)
				.build();
	}
}
