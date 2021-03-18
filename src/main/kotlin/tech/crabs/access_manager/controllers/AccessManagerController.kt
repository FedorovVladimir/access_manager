package tech.crabs.access_manager.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import tech.crabs.access_manager.entities.Function
import tech.crabs.access_manager.entities.Role
import tech.crabs.access_manager.services.AccessManagerService
import javax.inject.Inject

@Controller
class AccessManagerController {

    @Inject
    private lateinit var accessManagerService: AccessManagerService

    @Get("/roles")
    fun getAllRoles(): List<Role> {
        return accessManagerService.getRoles()
    }

    @Post("/roles")
    fun postRoles(@Body role: Role): HttpResponse<Role> {
        return HttpResponse.created(accessManagerService.addRole(role))
    }

    @Get("/functions")
    fun getAllAccesses(): List<Function> {
        return accessManagerService.getFunctions()
    }

    @Post("/functions")
    fun postAccesses(@Body function: Function): HttpResponse<Function> {
        return HttpResponse.created(accessManagerService.addFunction(function))
    }
}
