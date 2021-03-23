package tech.crabs.access_manager.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.micronaut.views.View
import io.swagger.v3.oas.annotations.Hidden

@Hidden
@Controller("/")
@Secured(SecurityRule.IS_ANONYMOUS)
class ViewsController {

    @View("index")
    @Get("/")
    fun index(): HttpResponse<Any> {
        return HttpResponse.ok()
    }
}
