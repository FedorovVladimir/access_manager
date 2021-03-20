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
    fun getRoleByCode(code: String): RoleInfo {
        return accessManagerService.getRole(code)
    }

    @Post("/roles")
    fun addRole(@Body role: Role): HttpResponse<Role> {
        return HttpResponse.created(accessManagerService.addRole(role))
    }

    @Delete("/roles/{code}")
    fun deleteRoleByCode(code: String) {
        accessManagerService.deleteRole(code)
    }

    @Get("/functions")
    fun getAllFunctions(): List<Function> {
        return accessManagerService.getFunctions()
    }

    @Post("/functions")
    fun addFunction(@Body function: Function): HttpResponse<Function> {
        return HttpResponse.created(accessManagerService.addFunction(function))
    }

    @Delete("/functions/{code}")
    fun deleteFunctionByCode(code: String) {
        accessManagerService.deleteFunction(code)
    }

    @Post("/permissions/{uuid}/change")
    fun changePermissionByUuid(uuid: UUID) {
        accessManagerService.changePermission(uuid)
    }
}
