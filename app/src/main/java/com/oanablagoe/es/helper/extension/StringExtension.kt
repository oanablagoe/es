@file:Suppress("UNCHECKED_CAST")

package com.oanablagoe.es.helper.extension

import android.annotation.SuppressLint
import android.text.Editable
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example
 *
 * val jsonString = "{ \"name\": \"Alex\"}
 *
 * val person = jsonString.fromJson<Person>()
 */
inline fun <reified T> String.fromJson(): T = jacksonObjectMapper()
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    .readValue(this)

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

@SuppressLint("SimpleDateFormat")
fun String.toDate(pattern: String = "yyyy-MM-dd'T'HH:mm:ss.SSS"): Date? {
    val formatter = SimpleDateFormat(pattern)
    return formatter.parse(this)
}
