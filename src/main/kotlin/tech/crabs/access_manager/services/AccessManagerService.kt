package tech.crabs.access_manager.services

import tech.crabs.access_manager.entities.Function
import tech.crabs.access_manager.entities.Role
import tech.crabs.access_manager.entities.RoleInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessManagerService {

    @Inject
    private lateinit var roleRepository: RoleRepository

    @Inject
    private lateinit var functionRepository: FunctionRepository

    fun getRoles(): List<RoleInfo> {
        return roleRepository.findAll().map {
            RoleInfo(
                it.code!!,
                it.name!!,
                emptyList()
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
        return functionRepository.save(function)
    }
}
