package tech.crabs.access_manager

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
    info = Info(
        title = "access_manager",
        version = "0.1",
        description = "Вспомогательное ПО для контроля разрешений пользователей с различными ролями на доступ к той или иной функциональности основного ПО.",
        contact = Contact(
            url = "https://github.com/FedorovVladimir",
            name = "Fedorov Vladimir",
            email = "vladimir.fodorow@gmail.com"
        )
    )
)
object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.run(Application.javaClass)
    }
}
