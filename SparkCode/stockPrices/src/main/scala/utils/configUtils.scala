package utils

import com.typesafe.config.{Config, ConfigFactory}

object configUtils {

  def getConfig(): Config = {
    ConfigFactory
      .parseResources(f"Config/application.conf")
      .resolve()
      .getConfig("application")
  }
}
