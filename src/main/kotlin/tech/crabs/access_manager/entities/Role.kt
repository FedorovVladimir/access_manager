package tech.crabs.access_manager.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "roles")
data class Role(

    @Id
    var code: String,

    var name: String,
)
