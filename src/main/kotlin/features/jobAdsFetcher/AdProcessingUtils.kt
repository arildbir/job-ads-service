package features.jobAdsFetcher

import integrations.Ads
import integrations.containsKotlinOrJava
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields

object AdProcessingUtils {
    fun getYearWeek(publishedDate: String): String {
        val expectedDatePattern = Regex("""\d{4}-\d{2}-\d{2}""")
        require(publishedDate.substring(0, 10).matches(expectedDatePattern)) { "Date format should start with YYYY-MM-DD. Was $publishedDate" }

        val weekFields = WeekFields.ISO

        val date = LocalDate.parse(publishedDate.substring(0, 10), DateTimeFormatter.ISO_DATE)
        val week = date.get(weekFields.weekOfWeekBasedYear())
        val year = date.get(weekFields.weekBasedYear())

        return "${year}-W${week.toString().padStart(2, '0')}"
    }

    fun filterRelevantAds(ads: Ads): List<RelevantAd> {
        return ads.content.mapNotNull { ad ->
            val (isKotlin, isJava) = ad.containsKotlinOrJava()
            when {
                isKotlin && isJava -> RelevantAd.KotlinAndJavaAd(
                    title = ad.title,
                    published = ad.published,
                    description = ad.description,
                    isKotlin = true,
                    isJava = true
                )
                isKotlin -> RelevantAd.KotlinAd(
                    title = ad.title,
                    published = ad.published,
                    description = ad.description,
                    isKotlin = true,
                    isJava = false
                )
                isJava -> RelevantAd.JavaAd(
                    title = ad.title,
                    published = ad.published,
                    description = ad.description,
                    isKotlin = false,
                    isJava = true
                )
                else -> null
            }
        }
    }
}
