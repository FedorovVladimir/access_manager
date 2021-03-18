package tech.crabs.access_manager.services

import tech.crabs.access_manager.entities.Function
import tech.crabs.access_manager.entities.Role
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessManagerService {

    @Inject
    private lateinit var roleRepository: RoleRepository

    @Inject
    private lateinit var functionRepository: FunctionRepository

    fun getRoles(): List<Role> {
        return roleRepository.findAll().toList()
    }

    fun addRole(role: Role): Role {
        return roleRepository.save(role)
    }

    fun getFunctions(): List<Function> {
        return functionRepository.findAll().toList()
    }

    fun addFunction(function: Function): Function {
        return functionRepository.save(function)
    }
}
