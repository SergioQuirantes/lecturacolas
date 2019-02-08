CREATE KEYSPACE IF NOT EXISTS cassandra_keyspace WITH replication = 
{'class':'SimpleStrategy','replication_factor':'1'};