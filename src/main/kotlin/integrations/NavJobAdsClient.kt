package integrations

import application.NavJobAdsConfig
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

interface NavJobAdsClient {
    suspend fun getNavJobAdsContent(opts: NavJobAdsContentOpts): NavJobAdsResult
}

data class NavJobAdsContentOpts(
    val category: String? = null,
    val size: Int? = null,
    val dateRange: String? = null,
    val page: Int? = null
)

class NavJobAdsClientKtor(private val config: NavJobAdsConfig, private val httpClient: HttpClient): NavJobAdsClient {
    override suspend fun getNavJobAdsContent(opts: NavJobAdsContentOpts): NavJobAdsResult {
        val response = httpClient.get("${config.url}/public-feed/api/v1/ads") {
            headers {
                append("Authorization", "Bearer ${config.publicAccessToken}")
            }
            url {
                opts.category?.let { parameters.append("category", it) }
                opts.size?.let { parameters.append("size", it.toString()) }
                opts.dateRange?.let { parameters.append("published", it) }
                opts.page?.let { parameters.append("page", it.toString()) }
            }
        }

        return when (response.status) {
            HttpStatusCode.OK -> {
                NavJobAdsResult.Success(response.body<Ads>())
            }
            else -> {
                NavJobAdsResult.Error("Status code was: ${response.status}. Message: Failed to fetch job ads}")
            }
        }
    }
}


sealed class NavJobAdsResult {
    @Serializable
    data class Success(val ads: Ads) : NavJobAdsResult()

    @Serializable
    data class Error(val message: String) : NavJobAdsResult()
}
