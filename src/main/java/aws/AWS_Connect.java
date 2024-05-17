package aws;



import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;

import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.amazonaws.services.dynamodbv2.document.*;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

public class AWS_Connect {

	private String accessKey, region, secretKey, bucketName, tableName, endpoint;
	private BasicAWSCredentials credentials;
	private AmazonS3 s3Client;
	private Bucket bucket;
	private AmazonDynamoDB clientDB;
	private DynamoDB dynamoDB;
	private Table table;
	private Item item;
	private PutItemSpec putItemSpec;
	private DynamoDbClient dynamoDbClient;
	private ScanSpec scanSpec;
	//private ScanOutcome scanOutcome;
	private ItemCollection<ScanOutcome> scanOutcome;
	private software.amazon.awssdk.services.dynamodb.model.ScanRequest scanRequest;
	private ScanResponse scanResponse;
	//private GetItemOutcome
	//private ScanOutcome outcome;
	private DescribeTableRequest describeTableRequest;
	private DescribeTableResult describeTableResult;
	private TableDescription tableDescription;	
	private DeleteItemRequest deleteRequest;
	private DeleteItemSpec deleteItemSpec;
	private PutObjectRequest putObjectRequest;
	private AwsBasicCredentials awsCredentials;
	//ScanOutcome scanOutcome = table.scan();

	public DeleteItemSpec getDeleteItemSpec() {
		return deleteItemSpec;
	}

	public void setDeleteItemSpec(DeleteItemSpec deleteItemSpec) {
		this.deleteItemSpec = deleteItemSpec;
	}

	public DeleteItemRequest getDeleteRequest() {
		return deleteRequest;
	}

	public void setDeleteRequest(DeleteItemRequest deleteRequest) {
		this.deleteRequest = deleteRequest;
	}

	public DescribeTableRequest getDescribeTableRequest() {
		return describeTableRequest;
	}

	public void setDescribeTableRequest(DescribeTableRequest describeTableRequest) {
		this.describeTableRequest = describeTableRequest;
	}

	public DescribeTableResult getDescribeTableResult() {
		return describeTableResult;
	}

	public void setDescribeTableResult(DescribeTableResult describeTableResult) {
		this.describeTableResult = describeTableResult;
	}

	public TableDescription getTableDescription() {
		return tableDescription;
	}

	public void setTableDescription(TableDescription tableDescription) {
		this.tableDescription = tableDescription;
	}

	public long getTableSize() {
		return tableSize;
	}

	public void setTableSize(long tableSize) {
		this.tableSize = tableSize;
	}

	private long tableSize;

	public software.amazon.awssdk.services.dynamodb.model.ScanRequest getScanRequest() {
		return scanRequest;
	}

	public void setScanRequest(software.amazon.awssdk.services.dynamodb.model.ScanRequest scanRequest) {
		this.scanRequest = scanRequest;
	}

	public ItemCollection<ScanOutcome> getScanOutcome() {
		return scanOutcome;
	}

	public void setScanOutcome(ItemCollection<ScanOutcome> scanOutcome) {
		this.scanOutcome = scanOutcome;
	}

	public ScanSpec getScanSpec() {
		return scanSpec;
	}

	public void setScanSpec(ScanSpec scanSpec) {
		this.scanSpec = scanSpec;
	}



	public DynamoDbClient getDynamoDbClient() {
		return dynamoDbClient;
	}

	public void setDynamoDbClient(DynamoDbClient dynamoDbClient) {
		this.dynamoDbClient = dynamoDbClient;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public BasicAWSCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(BasicAWSCredentials credentials) {
		this.credentials = credentials;
	}

	public AmazonS3 getS3Client() {
		return s3Client;
	}

	public void setS3Client(AmazonS3 s3Client) {
		this.s3Client = s3Client;
	}

	public Bucket getBucket() {
		return bucket;
	}

	public void setBucket(Bucket bucket) {
		// this.bucket = bucket;
		if (!s3Client.doesBucketExistV2(bucketName)) {
			// Nếu chưa tồn tại, tạo mới
			this.bucket = s3Client.createBucket(bucketName);
			System.out.println("Bucket " + bucket.getName() + " đã được tạo.");
		} else {
			System.out.println("Bucket " + bucketName + " đã tồn tại.");
			this.bucket =null;
		}
	}

	public AmazonDynamoDB getClientDB() {
		return clientDB;
	}

	public void setClientDB(AmazonDynamoDB clientDB) {
		this.clientDB = clientDB;
	}

	public DynamoDB getDynamoDB() {
		return dynamoDB;
	}

	public void setDynamoDB(DynamoDB dynamoDB) {
		this.dynamoDB = dynamoDB;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public PutItemSpec getPutItemSpec() {
		return putItemSpec;
	}

	public void setPutItemSpec(PutItemSpec putItemSpec) {
		this.putItemSpec = putItemSpec;
	}

	public AWS_Connect() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AWS_Connect(String accessKey, String region, String secretKey, String bucketName, String tableName
			) {
		super();
		this.accessKey = accessKey;
		this.region = region;
		this.secretKey = secretKey;
		this.bucketName = bucketName;
		this.tableName = tableName;
		this.endpoint = "https://dynamodb."+region+".amazonaws.com";
		this.credentials = new BasicAWSCredentials(accessKey, secretKey);
		
		this.s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(region).build();
		this.clientDB =  AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
		this.dynamoDB = new DynamoDB(clientDB);
		this.table = dynamoDB.getTable(tableName);
		this.scanSpec = new ScanSpec();
		this.scanOutcome = table.scan(scanSpec);
		//this.scanResponse = dynamoDbClient.scan(scanRequest);
		//this.outcome = table.scan();
		this.describeTableRequest = new DescribeTableRequest().withTableName(tableName);
		this.describeTableResult = clientDB.describeTable(describeTableRequest);
		this.tableDescription = describeTableResult.getTable();
		this.tableSize = tableDescription.getItemCount();
		this.awsCredentials= AwsBasicCredentials.create(
	    		accessKey,
	    		secretKey
	    );
		this.dynamoDbClient= DynamoDbClient.builder()
                .region(Region.of("ap-southeast-1")) // Thay thế với region của bạn
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();	
		//this.dynamoDbClient = DynamoDbClient.create();	
		this.scanRequest=ScanRequest.builder()
                .tableName(tableName) // Thay thế với tên bảng của bạn
                .build();
		this.scanResponse=dynamoDbClient.scan(scanRequest);
		
	}
	

	public ScanResponse getScanResponse() {
		return scanResponse;
	}

	public void setScanResponse(ScanResponse scanResponse) {
		this.scanResponse = scanResponse;
	}

	public PutObjectRequest getPutObjectRequest() {
		return putObjectRequest;
	}

	public void setPutObjectRequest(PutObjectRequest putObjectRequest) {
		this.putObjectRequest = putObjectRequest;
	}

	@Override
	public String toString() {
		return "AWS_Connect [tableSize=" + tableSize + "]";
	}


	
}
