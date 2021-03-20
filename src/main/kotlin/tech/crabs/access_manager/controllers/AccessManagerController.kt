package tech.crabs.access_manager.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import tech.crabs.access_manager.entities.Function
import tech.crabs.access_manager.entities.Role
import tech.crabs.access_manager.entities.RoleInfo
import tech.crabs.access_manager.services.AccessManagerService
import java.util.*
import javax.inject.Inject

@Controller
class AccessManagerController {

    @Inject
    private lateinit var accessManagerService: AccessManagerService

    @Get("/roles")
    fun getAllRoles(): List<RoleInfo> {
        return accessManagerService.getRoles()
    }

    @Get("/roles/{code}")
    fun getRole(code:String): RoleInfo {
        return accessManagerService.getRole(code)
    }

    @Delete("/roles/{code}")
    fun deleteRole(code:String) {
        accessManagerService.deleteRole(code)
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

    @Delete("/functions/{code}")
    fun deleteFunction(code:String) {
        accessManagerService.deleteFunction(code)
    }

    @Post("/permissions/{uuid}/change")
    fun changePermission(uuid: UUID) {
        accessManagerService.changePermission(uuid)
    }
}
