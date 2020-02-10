package utils

import org.apache.spark.sql.{DataFrame, SaveMode}
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf

object extensions {

  implicit class DataFrameExtensions(val df: DataFrame) extends AnyVal {

    def writeAsCSV(): Unit = {
      df.write.format("parquet").mode(SaveMode.Overwrite).save("s3://home-laboratory/analitico/result")
    }
    
  }

  def getAverage: UserDefinedFunction = udf((min: Double,max:Double) => {
    (min + max) / 2
  })

}
