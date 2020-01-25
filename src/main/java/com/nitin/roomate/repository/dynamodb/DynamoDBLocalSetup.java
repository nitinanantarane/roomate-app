package com.nitin.roomate.repository.dynamodb;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.nitin.roomate.Roomate;
import com.nitin.roomate.RoomateController;
import com.nitin.roomate.repository.dynamodb.model.RoomateInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

//@Configuration
public class DynamoDBLocalSetup {
    //private static RoomateRepository roomateRepository = initSchema();

    private static DynamoDBMapper dynamoDBMapper;
    private static AmazonDynamoDB amazonDynamoDB;


    private static final String DYNAMODB_ENDPOINT = "amazon.dynamodb.endpoint";
    private static final String AWS_ACCESSKEY = "amazon.aws.accesskey";
    private static final String AWS_SECRETKEY = "amazon.aws.secretkey";

    private static boolean isEmpty(String inputString) {
        return inputString == null || "".equals(inputString);
    }

    static {
        initSchema();
    }

    //@Bean
    public static RoomateRepository getRoomateRepository() {
        RoomateRepository roomateRepository = new RoomateRepository();
        roomateRepository.setMapper(dynamoDBMapper);
        return roomateRepository;
    }

    private static void initSchema() {
        Properties testProperties = loadFromFileInClasspath("db.properties")
                .filter(properties -> !isEmpty(properties.getProperty(AWS_ACCESSKEY)))
                .filter(properties -> !isEmpty(properties.getProperty(AWS_SECRETKEY)))
                .filter(properties -> !isEmpty(properties.getProperty(DYNAMODB_ENDPOINT)))
                .orElseThrow(() -> new RuntimeException("Unable to get all of the required test property values"));

        String amazonAWSAccessKey = testProperties.getProperty(AWS_ACCESSKEY);
        String amazonAWSSecretKey = testProperties.getProperty(AWS_SECRETKEY);
        String amazonDynamoDBEndpoint = testProperties.getProperty(DYNAMODB_ENDPOINT);

        amazonDynamoDB = new AmazonDynamoDBClient(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
        amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        try {
            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(RoomateInfo.class);
            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
            amazonDynamoDB.createTable(tableRequest);
        } catch (ResourceInUseException e) {
            e.printStackTrace();
        }
    }

    private static Optional<Properties> loadFromFileInClasspath(String fileName) {
        InputStream stream = null;
        try {
            Properties config = new Properties();
			/*Path configLocation = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
			stream = Files.newInputStream(configLocation);
			config.load(stream);*/
            InputStream input = RoomateController.class.getClassLoader().getResourceAsStream("db.properties");
            config.load(input);

            return Optional.of(config);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
