package tech.crabs.access_manager.services

import tech.crabs.access_manager.entities.Function
import tech.crabs.access_manager.entities.Permission
import tech.crabs.access_manager.entities.Role
import tech.crabs.access_manager.entities.RoleInfo
import java.util.*
import javax.inject.Singleton

@Singleton
class AccessManagerService {

    private var roles = LinkedList<Role>()

    private var functions = LinkedList<Function>()

    fun getRoles(): List<RoleInfo> {
        return roles.map { RoleInfo(it.code!!, it.name!!) }
    }

    fun addRole(role: Role): RoleInfo {
        val permissions = LinkedList<Permission>()
        functions.forEach { permissions.add(Permission(role, it)) }
        roles.add(role)
        return RoleInfo(
            role.code!!,
            role.name!!
        )
    }

    fun getFunctions(): List<Function> {
        return functions
    }

    fun addFunction(function: Function): Function {
        functions.add(function)
        return function
    }
}
