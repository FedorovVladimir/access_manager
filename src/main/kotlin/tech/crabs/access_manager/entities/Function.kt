package tech.crabs.access_manager.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "functions")
data class Function(

    @Id
    var code: String,

    var name: String
)
