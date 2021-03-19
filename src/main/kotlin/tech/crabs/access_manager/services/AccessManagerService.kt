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
        return roleRepository.findAll().map { convert(it) }
    }

    fun addRole(role: Role): Role {
        val r = roleRepository.save(role)
        val functions = functionRepository.findAll()
        permissionRepository.saveAll(functions.map { Permission(UUID.randomUUID(), r, it) })
        return r
    }

    fun getFunctions(): List<Function> {
        return functionRepository.findAllOrderByCode()
    }

    fun addFunction(function: Function): Function {
        val f = functionRepository.save(function)
        val roles = roleRepository.findAll()
        permissionRepository.saveAll(roles.map { Permission(UUID.randomUUID(), it, f) })
        return f
    }

    fun changePermission(uuid: UUID) {
        val p = permissionRepository.findByUuid(uuid)
        p.has = !p.has
        permissionRepository.update(p)
    }

    fun getRole(code: String): RoleInfo {
        return convert(roleRepository.findByCode(code))
    }

    private fun convert(o: Role): RoleInfo {
        return RoleInfo(
            o.code!!,
            o.name!!,
            permissionRepository.findByRoleOrderByFunction(o)
        )
    }
}
