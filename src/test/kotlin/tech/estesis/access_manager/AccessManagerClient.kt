package tech.estesis.access_manager

import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import tech.crabs.access_manager.data.Data
import tech.crabs.access_manager.entities.Function
import tech.crabs.access_manager.entities.FunctionInfo
import tech.crabs.access_manager.entities.RoleInfo
import java.util.*

@Client("/")
interface AccessManagerClient {

    @Post("/login")
    fun login(@Header authorization: String)

    @Get("/roles")
    fun getAllRoles(@Header authorization: String): List<RoleInfo>

    @Get("/roles/{code}")
    fun getRoleByCode(@Header authorization: String, code: String): RoleInfo

    @Post("/roles")
    fun addRole(@Header authorization: String, @Body role: RoleInfo?)

    @Delete("/roles/{code}")
    fun deleteRole(@Header authorization: String, code: String)

    @Get("/functions")
    fun getAllFunctions(@Header authorization: String): List<Function>

    @Post("/functions")
    fun addFunction(@Header authorization: String, @Body function: FunctionInfo?)

    @Delete("/functions/{code}")
    fun deleteFunctionByCode(@Header authorization: String, code: String)

    @Post("/permissions/{uuid}/change")
    fun changePermissionByUuid(@Header authorization: String, uuid: UUID)

    @Get("/data")
    fun getData(@Header authorization: String): Data

    @Post("/data")
    fun setData(@Header authorization: String, @Body data: Data)
}
