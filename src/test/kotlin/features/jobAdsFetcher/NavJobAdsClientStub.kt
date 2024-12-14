package features.jobAdsFetcher

import integrations.Ads
import integrations.NavJobAdsClient
import integrations.NavJobAdsContentOpts
import integrations.NavJobAdsResult

class NavJobAdsClientStub(
    private val testAds: Ads = TestAds.getTestAds(),
    private var runNumber: Int = 0
) : NavJobAdsClient {
    override suspend fun getNavJobAdsContent(opts: NavJobAdsContentOpts): NavJobAdsResult {
        if (runNumber == 0) {
            runNumber++
            return NavJobAdsResult.Success(testAds)
        } else {
            return NavJobAdsResult.Success(TestAds.getEmptyList())
        }

    }
}