Before start

Change username in H2JDBCUtils, this line:
```
public static final String jdbcUsername = "";
```

Create database H2 with parameters:
```
Connection-type: in-memory
Username: your username
URL: jdbc:h2:mem:bank;DB_ClOSE_DELAY=-1
```

Now you can start an application that automatically
creates six accounts with zero balance:
```
40817810400000000001
40817810400000000002
40817810400000000003
40817810400000000004
40817810400000000005
40817810400000000006
```
Example of REST requests to work with app:

GET requests
```
http://localhost:8080/showlistofcardsget
http://localhost:8080/checkbalanceforaccountget?accountnumber=40817810400000000001
```
POST requests
```
To create cards for accounts.
http://localhost:8080/createcardpost

JSON body:
{
	"accountNumber": "40817810400000000001"
}
```
```
To make a deposit for specific account:
http://localhost:8080/updateaccountpost

JSON body:
{
	"accountNumber": "40817810400000000001",
	"balance": 1500500
}
```
```
To make a transaction from one account to another:
http://localhost:8080/sendp2p
JSON body:
{
	"accountNumber": "40817810400000000001",
	"balance": 150,
	"accountNumber2": "40817810400000000002"
}
```


