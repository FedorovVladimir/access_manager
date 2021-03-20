package tech.crabs.access_manager.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.views.View
import io.swagger.v3.oas.annotations.Hidden
import tech.crabs.access_manager.entities.ResponseFunction
import tech.crabs.access_manager.entities.ResponsePermission
import tech.crabs.access_manager.entities.ResponseRole
import tech.crabs.access_manager.entities.RoleInfo
import tech.crabs.access_manager.services.AccessManagerService
import javax.inject.Inject

@Hidden
@Controller("/")
class ViewsController {

    @Inject
    private lateinit var accessManagerService: AccessManagerService

    @View("index")
    @Get("/")
    fun index(): HttpResponse<ResponseRole> {
        return HttpResponse.ok(ResponseRole(accessManagerService.getRoles()))
    }

    @View("roles")
    @Get("/page_roles")
    fun roles(): HttpResponse<ResponseRole> {
        return HttpResponse.ok(ResponseRole(accessManagerService.getRoles()))
    }

    @View("role")
    @Get("/page_role/{code}")
    fun roles(code: String): HttpResponse<RoleInfo> {
        return HttpResponse.ok(accessManagerService.getRole(code))
    }

    @View("functions")
    @Get("/page_functions")
    fun functions(): HttpResponse<ResponseFunction> {
        return HttpResponse.ok(ResponseFunction(accessManagerService.getFunctions()))
    }

    @View("permissions")
    @Get("/page_permissions")
    fun permissions(): HttpResponse<ResponsePermission> {
        return HttpResponse.ok(ResponsePermission(accessManagerService.getFunctions(), accessManagerService.getRoles()))
    }
}
