package tech.crabs.access_manager.services

import tech.crabs.access_manager.entities.*
import tech.crabs.access_manager.entities.Function
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
        return roleRepository.findAllOrderByCode().map { convert(it) }
    }

    fun addRole(role: RoleInfo): Role {
        if (roleRepository.existsByCode(role.code!!)) {
            throw Exception("Роль с кодом '${role.code}' уже существует")
        }
        if (roleRepository.existsByName(role.name!!)) {
            throw Exception("Роль с названием '${role.name}' уже существует")
        }
        val r = roleRepository.save(
            Role(role.code!!, role.name!!)
        )
        val functions = functionRepository.findAll()
        permissionRepository.saveAll(functions.map { Permission(UUID.randomUUID(), r, it) })
        return r
    }

    fun getFunctions(): List<Function> {
        return functionRepository.findAllOrderByCode()
    }

    fun addFunction(function: FunctionInfo): Function {
        if (functionRepository.existsByCode(function.code!!)) {
            throw Exception("Функция с кодом '${function.code}' уже существует")
        }
        if (functionRepository.existsByName(function.name!!)) {
            throw Exception("Функция с названием '${function.name}' уже существует")
        }
        val f = functionRepository.save(
            Function(function.code!!, function.name!!)
        )
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
            o.code,
            o.name,
            permissionRepository.findByRoleOrderByFunction(o)
        )
    }

    fun deleteRole(code: String) {
        roleRepository.deleteByCode(code)
    }

    fun deleteFunction(code: String) {
        functionRepository.deleteByCode(code)
    }
}
