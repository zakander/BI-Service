package com.zakander.stockscraperservice.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
		String secretAccessKey = "<>";
		
		try {
			File info = new File("info/info.txt");
			Scanner sc = new Scanner(info);
			accessKey = sc.nextLine();
			secretAccessKey = sc.nextLine();
			sc.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
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
										accessKey,
										secretAccessKey
								)
						)
				)
				.build();
	}
}
