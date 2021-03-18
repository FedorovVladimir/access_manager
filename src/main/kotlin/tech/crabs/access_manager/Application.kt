package tech.crabs.access_manager

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("tech.estesis")
        .start()
}

