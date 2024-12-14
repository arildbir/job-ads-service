package features.jobAdsFetcher

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual

class FetchJobAdsModuleTest : FunSpec({
    test("fetchJobAdsLastSixMonths should print relevant job ads per week") {
        val testAds = TestAds.getTestAds()
        val testAdsFiltered = AdProcessingUtils.filterRelevantAds(testAds)

        val fetchJobAdsModule = FetchJobAdsModule(NavJobAdsClientStub(testAds))
        val relevantAds = fetchJobAdsModule.fetchJobAdsLastSixMonths()

        relevantAds.first() shouldBeEqual testAdsFiltered.first()

    }
})
