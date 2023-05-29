package com.zakander.stockscraperservice.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@EnableDynamoDBRepositories
public class Config {		
//	@Bean
//	public DynamoDbClient amazonDynamoDB() {
//		String accessKey = "AKIAVP234CBC6Q2BVW6E";
//		String secretAccessKey = "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";
//		
//		DynamoDbClient client = DynamoDbClient.builder()
//				.endpointOverride(URI.create("http://localhost:8000"))
//				.region(Region.US_EAST_1)
//				.credentialsProvider(StaticCredentialsProvider.create(
//				AwsBasicCredentials.create(accessKey, secretAccessKey)))
//				.build();
//		return client;
//	}
//	
//	@Bean
//	public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
//		return DynamoDbEnhancedClient.builder()
//				.dynamoDbClient(amazonDynamoDB())
//				.build();
//	}
	
	private String accessKey = "<>";
	private String secretKey = "<>";
	private String endpoint = "dynamodb.us-east-1.amazonaws.com";
	private String region = "us-east-1";
	
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		EndpointConfiguration endpointConfig = new EndpointConfiguration (endpoint, region);
		AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder
				.standard()
				.withEndpointConfiguration(endpointConfig)
				.withCredentials(new AWSStaticCredentialsProvider(credentials));
		
		AmazonDynamoDB client = builder.build();
		return client;
	}
}
