package application

import integrations.NavJobAdsClient
import integrations.NavJobAdsClientKtor
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

data class Dependencies(
    val httpClient: HttpClient,
    val navJobAdsClient: NavJobAdsClient
) {
    companion object {
        fun from(config: JobAdsServiceConfig): Dependencies {

            val httpClient = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json(
                        Json { ignoreUnknownKeys = true }
                    )
                }
            }

            val navJobAdsClient = NavJobAdsClientKtor(config.navJobAdsConfig, httpClient)
            return Dependencies(
                httpClient = httpClient,
                navJobAdsClient = navJobAdsClient
            )
        }
    }
}