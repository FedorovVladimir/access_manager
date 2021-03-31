package tech.crabs.access_manager.data

import io.micronaut.core.annotation.Introspected
import tech.crabs.access_manager.entities.Function
import tech.crabs.access_manager.entities.Permission
import tech.crabs.access_manager.entities.Role

data class Data(

    val roles: List<Role>?,

    val functions: List<Function>?,

    val permissions: List<Permission>?
)
