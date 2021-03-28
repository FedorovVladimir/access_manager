package tech.crabs.access_manager.entities

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotEmpty

@Introspected
data class FunctionInfo(

    @field:NotEmpty(message = "Поле код не может быть пустым")
    var code: String?,

    @field:NotEmpty(message = "Поле название не может быть пустым")
    var name: String?,

    var permissions: List<Permission>? = null
)
