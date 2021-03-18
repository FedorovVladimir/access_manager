package tech.estesis

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import tech.estesis.entities.Function
import tech.estesis.entities.Role

@Client("/")
interface AccessManagerClient {

    @Get("/roles")
    fun getRoles(): List<Role>

    @Post("/roles")
    fun addRole(@Body role: Role)

    @Get("/functions")
    fun getAccesses(): List<Function>

    @Post("/functions")
    fun addAccess(function: Function)
}
