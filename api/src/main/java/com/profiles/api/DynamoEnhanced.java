package com.profiles.api;

import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primaryPartitionKey;

import com.profiles.api.model.Profile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.profiles.ProfileProperty;
import software.amazon.awssdk.protocols.query.internal.marshall.MapQueryMarshaller;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import javax.persistence.*;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.profiles.api.model.ProfileItems;

@Component("DynamoEnhanced")
public class DynamoEnhanced {
    private final static Logger LOGGER = Logger.getLogger(DynamoEnhanced.class.getName());

    DynamoDbTable<ProfileItems> mappedTable;

    public DynamoEnhanced() {
        Region region = Region.US_EAST_1;
        DynamoDbClient ddb = DynamoDbClient.builder().region(region)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create()).build();
        try {

            DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(ddb).build();

            // ListTablesResponse response = ddb.listTables();
            // for (String s : response.tableNames()) {
            //     LOGGER.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" + s);
            // }

            // Create a DynamoDbTable object
            
            //Profiles is a table that (hopefully) exists in the AWS account determined by the environment variable credentials
            mappedTable = enhancedClient.table("Profiles", TableSchema.fromBean(ProfileItems.class));
        } catch (Exception e) {
            LOGGER.info(e.toString());
            // e.printStackTrace();
        }
    }

    public Profile getRow(long partitionKey) {
        try {
            ProfileItems result = mappedTable.getItem(Key.builder().partitionValue(partitionKey).build());
            return new Profile(result.getId(), result.getName(), result.getEmail(), result.getPhone_number(),
                    result.getCity(), result.getState(), result.getTrack(), result.getAccount(),
                    result.getProject_code(), result.getStart_date());
        } catch (Exception e) {
            LOGGER.info(e.toString());
            // e.printStackTrace();
            return null;
        }
    }

    // Uses the enhanced client to inject a new post into a DynamoDB table
    public void putRow(Profile item) {
        try {
            ProfileItems gi = new ProfileItems();
            gi.setId(item.get_id());
            gi.setName(item.get_name());
            gi.setEmail(item.get_email());
            gi.setPhone_number(item.get_phone_number());
            gi.setCity(item.get_city());
            gi.setState(item.get_state());
            gi.setTrack(item.get_track());
            gi.setAccount(item.get_account());
            gi.setProject_code(item.get_project_code());
            gi.setStart_date(item.get_start_date());

            PutItemEnhancedRequest enReq = PutItemEnhancedRequest.builder(ProfileItems.class).item(gi).build();

            mappedTable.putItem(enReq);

        } catch (Exception e) {
            LOGGER.info(e.toString());
            // e.printStackTrace();
        }
    }

}
