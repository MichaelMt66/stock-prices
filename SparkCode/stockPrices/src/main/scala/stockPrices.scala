import utils.{configUtils, sparkUtils}
import logic.applyLogic
import utils.extensions._
object stockPrices {

  def main(args: Array[String]): Unit = {

    val config = configUtils.getConfig()
    val spark = sparkUtils.getSparkSession()
    val hitoricalStockPriceDf = sparkUtils.getCSV(config.getString("stockPrices"), spark)
    val hitoricalStockDf = sparkUtils.getCSV(config.getString("stocks"), spark)
    hitoricalStockPriceDf.transform(applyLogic(hitoricalStockDf)).writeAsCSV()
    spark.stop()
  }

}
