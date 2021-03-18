package tech.crabs.access_manager.entities

data class Permission(

    var function: Function,

    var has: Boolean = false
)
