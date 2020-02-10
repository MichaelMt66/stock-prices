import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{avg, broadcast, col, window}
import utils.extensions.getAverage

object logic {

  def applyLogic(dataFrame: DataFrame)(df: DataFrame): DataFrame = {

    df.select("ticker","low","high","date")
      .withColumn("average_per_day",getAverage(col("low"),col("high")))
      .drop("low","high")
      .groupBy(col("ticker"),window(col("date"), "7 days", "1 days").as("range_days"))
      .agg(avg("average_per_day").as("average_per_range"))
      //.orderBy("range_days")
      .join(broadcast(dataFrame), Seq("ticker"))

  }
}
