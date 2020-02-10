package utils

import org.apache.spark.sql.{DataFrame, SparkSession}

object sparkUtils {


  def getSparkSession(appName: String = "stockPrices"): SparkSession = {

    val spark = SparkSession
      .builder()
      .appName(appName)
      .enableHiveSupport
      .config("spark.sql.sources.partitionOverwriteMode", "dynamic")
      .config("spark.sql.parquet.writeLegacyFormat", "true")
      .config("spark.rdd.compress", "true")
      .config("spark.sql.adaptive.enabled", "true")
      .config("hive.optimize.s3.query", "true")
      .config("hive.exec.parallel", "true")
      .config("hive.exec.dynamic.partition", "true")
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .config("spark.sql.parquet.cacheMetadata", "false")
      .getOrCreate()

    spark.conf.set("spark.sql.files.maxPartitionBytes", 12000000) //setting 12M, aprox 180 partitions
    spark.conf.set("spark.sql.shuffle.partitions", 180)
    spark
  }

  def getLocalSparkSession( appName: String = "stockPrices"): SparkSession = {

    val spark = SparkSession.builder
      .appName("Test")
      .master("local")
      .config("spark.debug.maxToStringFields", 2000)
      .getOrCreate()

    spark
  }

  def getCSV(path:String,spark:SparkSession): DataFrame = {
    spark.read.format("csv").option("inferSchema",true).option("header",true).load(path)
  }
}
