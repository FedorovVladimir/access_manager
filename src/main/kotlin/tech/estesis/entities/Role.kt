package tech.estesis.entities

import java.util.*

data class Role(

    var code: String? = null,

    var name: String? = null,

    var permissions: LinkedList<Permission>? = null
)
