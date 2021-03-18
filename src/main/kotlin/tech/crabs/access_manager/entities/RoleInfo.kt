package tech.crabs.access_manager.entities

data class RoleInfo(

    var code: String,

    var name: String,

    var permissions: List<Permission>?
)
