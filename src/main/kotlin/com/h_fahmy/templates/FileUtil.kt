package com.h_fahmy.templates

import kotlinx.serialization.json.JsonObject
import java.io.File

typealias Path = String

fun writeToFile(result: JsonObject): Path {
    val parent = File("").absoluteFile
    val file = File(parent, "templates.json")
    file.writeText(result.toString())
    return file.absolutePath
}

fun readTemplates(): String {
    val parent = File("").absoluteFile
    val file = File(parent, "templates.json")
    return file.readText()
}