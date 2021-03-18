package tech.crabs.access_manager.entities

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

//@Entity
//@Table(name = "permissions")
data class Permission(

//    @OneToMany
    var role: Role,

//    @OneToMany
    var function: Function,

    var has: Boolean = false
)
