package application

import com.typesafe.config.ConfigFactory
import io.github.config4k.extract
import org.slf4j.Logger
import org.slf4j.LoggerFactory

enum class Environment {
    LOCAL,
    DEV,
    PROD
}

data class NavJobAdsConfig(
    val url: String,
    val publicAccessToken: String,
)

data class JobAdsServiceConfig(
    val environment: Environment,
    val navJobAdsConfig: NavJobAdsConfig
) {
    companion object {
        fun init(): JobAdsServiceConfig {
            val environment = Environment.valueOf(System.getenv("ENVIRONMENT") ?: Environment.LOCAL.name)
            val config = ConfigFactory.load("jobsadsservice-${environment.name.lowercase()}.conf")
            val navJobAdsConfig = config.extract<NavJobAdsConfig>("navJobAdsConfigSettings")

            log.info("Environment is set to: $environment")

            return JobAdsServiceConfig(environment, navJobAdsConfig)
        }

        private val log: Logger = LoggerFactory.getLogger(JobAdsServiceConfig::class.java)

    }
}

