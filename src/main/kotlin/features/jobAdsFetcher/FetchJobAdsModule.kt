package features.jobAdsFetcher

import integrations.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class FetchJobAdsModule(private val navJobAdsClient: NavJobAdsClient) {
    fun printRelevantJobAdsPerWeek(fetchJobAds: List<RelevantAd>) {
        log.info("Total number of relevant ads: ${fetchJobAds.size}")

        val jobAdsPerCategory = fetchJobAds.groupBy {
            when (it) {
                is RelevantAd.KotlinAndJavaAd -> "Kotlin and Java"
                is RelevantAd.KotlinAd -> "Kotlin"
                is RelevantAd.JavaAd -> "Java"
            }
        }
        val jobAdsPerCategoryPerWeek = jobAdsPerCategory.mapValues { (_, ads) ->
            ads.groupBy { AdProcessingUtils.getYearWeek(it.published) }
        }

        jobAdsPerCategoryPerWeek.forEach { (category, adsPerWeek) ->
            log.info("Category: $category")
            adsPerWeek.forEach { (week, ads) ->
                log.info("Week: $week. Number of ads: ${ads.size}")
            }
        }
    }

    suspend fun fetchJobAdsLastSixMonths(): List<RelevantAd> = coroutineScope {
        val sixMonthsAgo = LocalDate.now().minusMonths(6).format(DateTimeFormatter.ISO_DATE)
        val dateRangePastSixMonths = "[$sixMonthsAgo,now]"

        log.info("Six months ago: $sixMonthsAgo")
        log.info("Date range: $dateRangePastSixMonths")

        var page = 0

        generateSequence {
            val opts = NavJobAdsContentOpts(dateRange = dateRangePastSixMonths, category = "IT", size = 300, page = page)

            val result = runBlocking { navJobAdsClient.getNavJobAdsContent(opts) }
            when (result) {
                is NavJobAdsResult.Success -> {
                    if (result.ads.content.isEmpty()) {
                        return@generateSequence null
                    }

                    AdProcessingUtils.filterRelevantAds(result.ads).apply { page++ }
                }
                is NavJobAdsResult.Error -> {
                    log.error("Failed to fetch job ads: ${result.message}")
                    null
                }
            }
        }.flatten().toList()
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(FetchJobAdsModule::class.java)
    }
}

sealed class RelevantAd(
    open val title: String,
    open val published: String,
) {
    data class KotlinAndJavaAd(
        override val title: String,
        override val published: String,
        val description: String?,
        val isKotlin: Boolean,
        val isJava: Boolean
    ) : RelevantAd(title, published)

    data class KotlinAd(
        override val title: String,
        override val published: String,
        val description: String?,
        val isKotlin: Boolean,
        val isJava: Boolean
    ) : RelevantAd(title, published)

    data class JavaAd(
        override val title: String,
        override val published: String,
        val description: String?,
        val isKotlin: Boolean,
        val isJava: Boolean
    ) : RelevantAd(title, published)
}
