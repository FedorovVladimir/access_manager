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
    fun getAllRoles(): List<RoleInfo>

    @Get("/roles/{code}")
    fun getRoleByCode(code: String): RoleInfo

    @Post("/roles")
    fun addRole(@Body role: Role)

    @Delete("/roles/{code}")
    fun deleteRole(code: String)

    @Get("/functions")
    fun getAllFunctions(): List<Function>

    @Post("/functions")
    fun addFunction(@Body function: Function)

    @Delete("/functions/{code}")
    fun deleteFunctionByCode(code: String)

    @Post("/permissions/{uuid}/change")
    fun changePermissionByUuid(uuid: UUID)
}
