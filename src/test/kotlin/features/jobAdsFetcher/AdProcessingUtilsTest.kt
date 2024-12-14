package features.jobAdsFetcher

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class AdProcessingUtilsTest : FunSpec({
    test("getYearWeek should return correct year and week for a valid date") {
        AdProcessingUtils.getYearWeek("2025-01-01T09:27:12.29+02:00") shouldBe "2025-W01"
        AdProcessingUtils.getYearWeek("2024-12-31T09:27:12.29+02:00") shouldBe "2025-W01"
        AdProcessingUtils.getYearWeek("2024-12-14T09:27:12.29+02:00") shouldBe "2024-W50"
        AdProcessingUtils.getYearWeek("2024-05-14T09:27:12.29+02:00") shouldBe "2024-W20"
    }

    test("getYearWeek should throw IllegalArgumentException for invalid date format") {
        val exception = shouldThrow<IllegalArgumentException> {
            AdProcessingUtils.getYearWeek("2023/01/05")
        }
        exception.message shouldBe "Date format should start with YYYY-MM-DD. Was 2023/01/05"
    }

    test("getYearWeek should throw DateTimeParseException for non-date string") {
        shouldThrow<IllegalArgumentException> {
            AdProcessingUtils.getYearWeek("invalid-date")
        }
    }

    test("filterRelevantAds should return relevant ads based on Kotlin and Java presence") {
        val testAds = TestAds.getTestAds()
        val result = AdProcessingUtils.filterRelevantAds(testAds)
        result.first().shouldBeInstanceOf<RelevantAd.KotlinAd>()
    }
})
