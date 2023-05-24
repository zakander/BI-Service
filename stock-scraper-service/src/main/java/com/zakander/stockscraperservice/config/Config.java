package com.zakander.stockscraperservice.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
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
	@Value("{amazom.awsaccesskey}")
	private String awsAccessKey;
	
	@Value("{amazom.awssecretaccesskey}")
	private String awsSecretAccessKey;
	
	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(amazonDynamoDB());
	}
	
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder
				.standard()
				.withEndpointConfiguration(
						new EndpointConfiguration(
								"dynamodb.us-east-1.amazonaws.com",
								"us-east-1"
						)
				)
				.withCredentials(
						new AWSStaticCredentialsProvider(
								new BasicAWSCredentials(
										awsAccessKey,
										awsSecretAccessKey
								)
						)
				)
				.build();
	}
	
//	@Bean
//	public AWSCredentials amazonAWSCredentials() {
//		return new BasicAWSCredentials(
//				"AKIAVP234CBC47SNRSVP", "53i6oU6zreKNM7MXeAsAri78QlJyzxswOrBZh8Xk");
//	}
//	
//	@Bean
//	public DynamoDbClient getDynamoDbClient() {
//		AwsCredentialsProvider credentialsProvider = DefaultCredentialsProvider
//				.builder()
//				.profileName("stockdbaccess")
//				.build();
//		
//		return DynamoDbClient.builder()
//				.region(Region.US_EAST_1)
//				.credentialsProvider(credentialsProvider).build();
//	}
//	
//	@Bean
//	public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
//		return DynamoDbEnhancedClient.builder()
//				.dynamoDbClient(getDynamoDbClient())
//				.build();
//	}
}
