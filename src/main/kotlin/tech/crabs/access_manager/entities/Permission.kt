package tech.crabs.access_manager.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "permissions")
data class Permission(

    @Id
    var uuid: UUID,

    @ManyToOne
    var role: Role?,

    @ManyToOne
    var function: Function?,

    var has: Boolean = false
)
