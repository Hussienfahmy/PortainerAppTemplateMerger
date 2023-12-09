package com.h_fahmy.templates

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.time.Duration

fun startUpdating(interval: Duration) {
    CoroutineScope(Dispatchers.IO).launch {
        val client = HttpClient(CIO)

        val urls = listOf(
            "https://raw.githubusercontent.com/xneo1/portainer_templates/master/Template/template.json",
            "https://raw.githubusercontent.com/portainer/templates/master/templates-2.0.json",
            "https://raw.githubusercontent.com/dnburgess/self-hosted-template/master/template.json",
            "https://raw.githubusercontent.com/Qballjos/portainer_templates/master/Template/template.json",
            "https://raw.githubusercontent.com/SelfhostedPro/selfhosted_templates/portainer-2.0/Template/template.json",
            "https://raw.githubusercontent.com/technorabilia/portainer-templates/main/lsio/templates/templates-2.0.json",
            "https://raw.githubusercontent.com/mikestraney/portainer-templates/master/templates.json",
            "https://raw.githubusercontent.com/mycroftwilde/portainer_templates/master/Template/template.json",
            "https://raw.githubusercontent.com/ntv-one/portainer/main/template.json"
        )

        // map the urls to a list of template json objects
        urls.flatMap { url ->
            client.get(url)
                .body<String>()
                .let { responseString ->
                    // parse the response string into a json object
                    Json.decodeFromString<JsonObject>(responseString).let { responseJson ->
                        // get the templates array from the json object
                        responseJson["templates"]?.jsonArray?.toList().also {
                            println("fetched ${it?.size} templates from $url")
                        } ?: emptyList()
                    }
                }
        }.also {
            println("fetched ${it.size} templates")
        }.distinctBy {
            // combine the title and name fields to get a unique identifier
            it.jsonObject.run {
                get("title")?.jsonPrimitive?.content ?: ""
                    .plus(get("name")?.jsonPrimitive?.content ?: "")
            }
        }.run {
            // this is the combined list of templates
            val combinedTemplates = JsonArray(this)
            println("combined distinct ${combinedTemplates.size} templates")
            // final result json object
            val result = JsonObject(
                mapOf(
                    "version" to JsonPrimitive("2"),
                    "templates" to combinedTemplates
                )
            )
            // write the result to a file
            val path = writeToFile(result)
            println("wrote templates to $path")
        }

        // Wait for the interval to update again
        delay(interval)
    }
}
