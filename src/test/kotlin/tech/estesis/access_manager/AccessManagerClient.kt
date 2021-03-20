package tech.estesis.access_manager

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import tech.crabs.access_manager.entities.Function
import tech.crabs.access_manager.entities.Role
import tech.crabs.access_manager.entities.RoleInfo
import java.util.*

@Client("/")
interface AccessManagerClient {

    @Get("/roles")
    fun getRoles(): List<RoleInfo>

    @Post("/roles")
    fun addRole(@Body role: Role)

    @Get("/functions")
    fun getAccesses(): List<Function>

    @Post("/functions")
    fun addAccess(@Body function: Function)

    @Post("/permissions/{uuid}/change")
    fun changePermission(uuid: UUID)

    @Get("/roles/{code}")
    fun getRole(code: String): RoleInfo

    @Delete("/roles/{code}")
    fun deleteRole(code: String)
}
