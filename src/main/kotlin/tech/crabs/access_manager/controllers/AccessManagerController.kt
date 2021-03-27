package tech.crabs.access_manager.controllers

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.hateoas.Link
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.AuthorizationException
import io.micronaut.security.rules.SecurityRule
import io.micronaut.validation.Validated
import tech.crabs.access_manager.entities.Function
import tech.crabs.access_manager.entities.ResponseData
import tech.crabs.access_manager.entities.Role
import tech.crabs.access_manager.entities.RoleInfo
import tech.crabs.access_manager.services.AccessManagerService
import java.util.*
import javax.inject.Inject
import javax.validation.Valid

@Controller
@Validated
@Secured(SecurityRule.IS_AUTHENTICATED)
class AccessManagerController {

    @Inject
    private lateinit var accessManagerService: AccessManagerService

    @Post("/login")
    fun login(): ResponseData {
        return ResponseData("ok")
    }

    @Get("/roles")
    fun getAllRoles(): List<RoleInfo> {
        return accessManagerService.getRoles()
    }

    @Get("/roles/{code}")
    fun getRoleByCode(code: String): RoleInfo {
        return accessManagerService.getRole(code)
    }

    @Post("/roles")
    fun addRole(@Body @Valid role: RoleInfo): HttpResponse<Role> {
        return HttpResponse.created(accessManagerService.addRole(role))
    }

    @Delete("/roles/{code}")
    fun deleteRoleByCode(code: String): ResponseData {
        accessManagerService.deleteRole(code)
        return ResponseData("ok")
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
    fun deleteFunctionByCode(code: String): ResponseData {
        accessManagerService.deleteFunction(code)
        return ResponseData("ok")
    }

    @Post("/permissions/{uuid}/change")
    fun changePermissionByUuid(uuid: UUID): ResponseData {
        accessManagerService.changePermission(uuid)
        return ResponseData("ok")
    }

    @Error
    fun badRequest(request: HttpRequest<Any?>, e: Throwable): HttpResponse<JsonError?>? {
        val error = JsonError(e.message).link(Link.SELF, Link.of(request.uri))
        return when (e) {
            is AuthorizationException -> HttpResponse.unauthorized()
            else -> HttpResponse.badRequest(error)
        }
    }
}
