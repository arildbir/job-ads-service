package integrations

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Ads(
    val content: List<Ad>,
    val totalElements: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val totalPages: Int,
    val first: Boolean,
    val last: Boolean,
    val sort: String
)

@Serializable
data class Ad(
    val uuid: String,
    val published: String,
    val updated: String,
    val expires: String,
    val workLocations: List<JsonElement>,
    val title: String,
    val jobtitle: String?,
    val description: String?,
    val sourceurl: String?,
    val source: String?,
    val applicationDue: String?,
    val applicationUrl: String?,
    val occupationCategories: List<JsonElement>,
    val link: String?,
    val engagementtype: String?,
    val extent: String?,
    val starttime: String?,
    val positioncount: String?,
    val sector: String?,
    val employer: JsonElement?
)

fun Ad.containsKotlinOrJava(): Pair<Boolean, Boolean> {
    val hasKotlinTitle = this.title.contains("kotlin", ignoreCase = true)
    val hasKotlinDescription = this.description?.contains("kotlin", ignoreCase = true) ?: false

    val hasJavaTitle = this.title.contains("java", ignoreCase = true)
    val hasJavaDescription = this.description?.contains("java", ignoreCase = true) ?: false

    return Pair(hasKotlinTitle || hasKotlinDescription, hasJavaTitle || hasJavaDescription)
}
