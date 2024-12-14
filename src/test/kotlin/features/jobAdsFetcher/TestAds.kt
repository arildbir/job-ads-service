package features.jobAdsFetcher

import integrations.*

object TestAds {

    fun getTestAds(): Ads {
        return testAds
    }

    fun getEmptyList(): Ads {
        return Ads(
            content = emptyList(),
            totalElements = 284,
            pageNumber = 95,
            pageSize = 3,
            totalPages = 95,
            first = false,
            last = true,
            sort = "published:desc"
        )
    }

    data class EmptyList(
        val content: List<Ad> = emptyList(),
        val totalElements: Int = 284,
        val pageNumber: Int = 95,
        val pageSize: Int = 3,
        val totalPages: Int = 95,
        val first: Boolean = false,
        val last: Boolean = true,
        val sort: String = "published:desc"
    )

    private val  emptyList = EmptyList()

    private val testAds = Ads(
        content = listOf(
            Ad(
                uuid = "81353355-2857-4dff-9e46-2a33e32c538e",
                published = "2024-08-27T22:00:42.624Z",
                updated = "2024-08-29T06:04:43.158549Z",
                expires = "2025-02-27T23:00:00Z",
                workLocations = listOf(),
                title = "Graduate 2025 - Solution Engineer",
                jobtitle = null,
                description = "has kotlin in the description",
                sourceurl = "https://dede.devcompany",
                source = "IMPORTAPI",
                applicationDue = null,
                applicationUrl = "https://devcompany.devcompany",
                occupationCategories = listOf(),
                link = "https://devcompany.devcompany",
                engagementtype = "Annet",
                extent = "Heltid",
                starttime = null,
                positioncount = "1",
                sector = "Privat",
                employer = null
            ),
            Ad(
                uuid = "81353355-2857-4dff-9e46-2a33e32c538e",
                published = "2024-08-27T22:00:42.624Z",
                updated = "2024-08-29T06:04:43.158549Z",
                expires = "2025-02-27T23:00:00Z",
                workLocations = listOf(),
                title = "Graduate 2025 - Solution Engineer",
                jobtitle = null,
                description = "has java in the description",
                sourceurl = "https://dede.devcompany",
                source = "IMPORTAPI",
                applicationDue = null,
                applicationUrl = "https://devcompany.devcompany",
                occupationCategories = listOf(),
                link = "https://devcompany.devcompany",
                engagementtype = "Annet",
                extent = "Heltid",
                starttime = null,
                positioncount = "1",
                sector = "Privat",
                employer = null
            ),
            Ad(
                uuid = "81353355-2857-4dff-9e46-2a33e32c538e",
                published = "2024-08-27T22:00:42.624Z",
                updated = "2024-08-29T06:04:43.158549Z",
                expires = "2025-02-27T23:00:00Z",
                workLocations = listOf(),
                title = "Graduate 2025 - Solution Engineer",
                jobtitle = null,
                description = "has kotlin and java in the description",
                sourceurl = "https://dede.devcompany",
                source = "IMPORTAPI",
                applicationDue = null,
                applicationUrl = "https://devcompany.devcompany",
                occupationCategories = listOf(),
                link = "https://devcompany.devcompany",
                engagementtype = "Annet",
                extent = "Heltid",
                starttime = null,
                positioncount = "1",
                sector = "Privat",
                employer = null
            )
        ),
        totalElements = 1,
        pageNumber = 0,
        pageSize = 10,
        totalPages = 1,
        first = true,
        last = true,
        sort = "published,desc"
    )
}


