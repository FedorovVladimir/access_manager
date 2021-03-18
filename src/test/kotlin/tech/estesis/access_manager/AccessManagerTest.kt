package tech.estesis.access_manager

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import tech.crabs.access_manager.entities.Function
import tech.crabs.access_manager.entities.Role
import tech.crabs.access_manager.services.FunctionRepository
import tech.crabs.access_manager.services.PermissionRepository
import tech.crabs.access_manager.services.RoleRepository
import javax.inject.Inject

@MicronautTest
class AccessManagerTest : StringSpec() {

    @Inject
    private lateinit var accessManagerClient: AccessManagerClient

    @Inject
    private lateinit var roleRepository: RoleRepository

    @Inject
    private lateinit var functionRepository: FunctionRepository

    @Inject
    private lateinit var permissionRepository: PermissionRepository

    init {
        "В новой системе ролей нет" {
            accessManagerClient.getRoles().size shouldBe 0
        }

        "В новой системе функций нет" {
            accessManagerClient.getAccesses().size shouldBe 0
        }

        "Добавляем роль 'Администратор'" {
            accessManagerClient.addRole(Role("ADMIN", "Администратор"))
        }

        "В системе есть одна роль" {
            accessManagerClient.getRoles().size shouldBe 1
        }

        "Добавляем функцию 'Создание задачи'" {
            accessManagerClient.addAccess(Function("create_task", "Создание задачи"))
        }

        "В системе есть одна функция" {
            accessManagerClient.getAccesses().size shouldBe 1
        }

        "У роли 'Администратор' есть одно право" {
            val role = accessManagerClient.getRoles()[0]
            val permissions = role.permissions
            permissions.shouldNotBeNull()
            permissions.size shouldBe 1
        }

        "Добавляем роль 'Оператор'" {
            accessManagerClient.addRole(Role("OPERATOR", "Оператор"))
        }

//        "У роли 'Оператор' есть одно право" {
//            val role = accessManagerClient.getRoles()[1]
//            val permissions = role.permissions
//            permissions.shouldNotBeNull()
//            permissions.size shouldBe 1
//        }
    }

    override fun afterSpec(spec: Spec) {
        permissionRepository.deleteAll()
        roleRepository.deleteAll()
        functionRepository.deleteAll()
    }
}
