package com.oanablagoe.es.helper.extension

import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Example
 *
 * val person = Person("Alex")
 *
 * person.toJson()
 */
fun Any.toJson(): String = ObjectMapper().writeValueAsString(this)
