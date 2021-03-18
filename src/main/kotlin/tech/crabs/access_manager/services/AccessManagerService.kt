package tech.crabs.access_manager.services

import tech.crabs.access_manager.entities.Function
import tech.crabs.access_manager.entities.Permission
import tech.crabs.access_manager.entities.Role
import tech.crabs.access_manager.entities.RoleInfo
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessManagerService {

    @Inject
    private lateinit var roleRepository: RoleRepository

    @Inject
    private lateinit var functionRepository: FunctionRepository

    @Inject
    private lateinit var permissionRepository: PermissionRepository

    fun getRoles(): List<RoleInfo> {
        return roleRepository.findAll().map {
            RoleInfo(
                it.code!!,
                it.name!!,
                permissionRepository.findByRole(it)
            )
        }
    }

    fun addRole(role: Role): Role {
        return roleRepository.save(role)
    }

    fun getFunctions(): List<Function> {
        return functionRepository.findAll().toList()
    }

    fun addFunction(function: Function): Function {
        val f = functionRepository.save(function)
        val roles = roleRepository.findAll()
        permissionRepository.saveAll(roles.map { Permission(UUID.randomUUID(), it, f) })
        return f
    }
}
