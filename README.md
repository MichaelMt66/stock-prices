
# stock-prices
Emr and Spark application


### Inputs
  * Both inputFiles (historical_stock_prices and historical_stocks) are stored in a S3 bucket called s3://home-laboratory/analitico/data/


### Spark code
 * To generare .jar file, it must run ./compile.sh file in a server with sbt already isntalled
 * Move the .jar file generated to s3://home-laboratory/analitico/jars/


### Cloudformation
  *  Using Aws Cloudformation service (in Virgina region) create a new stack where the templates are placed in s3://home-laboratory/analitico/templates/ where the main template is main.yaml
  *  The Cloudformation template will create an VPC with Private/Public subnet, security groups, roles, EMR Cluster (a master and a worker node) and EMR Step, which will run the .jar file, aftter the execution the EMR cluster is deteled.
