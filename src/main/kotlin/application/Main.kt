package application

import features.jobAdsFetcher.FetchJobAdsModule
import kotlinx.coroutines.runBlocking

fun main() {
    val config = JobAdsServiceConfig.init()
    val dependencies = Dependencies.from(config)

    Application(dependencies)
}

class Application(private val dependencies: Dependencies) {
    init {
        runBlocking {
            val fetchJobAdsModule = FetchJobAdsModule(dependencies.navJobAdsClient)

            val fetchedJobAds = fetchJobAdsModule.fetchJobAdsLastSixMonths()
            fetchJobAdsModule.printRelevantJobAdsPerWeek(fetchedJobAds)
        }
    }
}
