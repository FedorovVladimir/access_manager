package tech.estesis.services

import tech.estesis.entities.Function
import tech.estesis.entities.Permission
import tech.estesis.entities.Role
import java.util.*
import javax.inject.Singleton

@Singleton
class AccessManagerService {

    private var roles = LinkedList<Role>()

    private var functions = LinkedList<Function>()

    fun getRoles(): List<Role> {
        return roles
    }

    fun addRole(role: Role): Role {
        val permissions = LinkedList<Permission>()
        functions.forEach { function -> permissions.add(Permission(function)) }
        role.permissions = permissions
        roles.add(role)
        return role
    }

    fun getFunctions(): List<Function> {
        return functions
    }

    fun addFunction(function: Function): Function {
        for (role in roles) {
            role.permissions!!.add(Permission(function))
        }
        functions.add(function)
        return function
    }
}
