package tech.estesis.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.views.View
import tech.estesis.entities.ResponseFunction
import tech.estesis.entities.ResponsePermission
import tech.estesis.entities.ResponseRole
import tech.estesis.services.AccessManagerService
import javax.inject.Inject

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
