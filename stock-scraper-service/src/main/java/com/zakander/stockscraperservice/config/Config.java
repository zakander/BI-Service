package com.zakander.stockscraperservice.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.zakander.stockscraperservice.repository")
public class Config {
	@Value("${amazon.aws.accesskey}")
	private String accessKey;
	
	@Value("${amazon.aws.secretkey}")
	private String secretKey;
	
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(amazonDynamoDB());
	}
	
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder
				.standard()
				.withRegion(Regions.US_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(credentials));
		
		AmazonDynamoDB client = builder.build();
		return client;
	}
}
