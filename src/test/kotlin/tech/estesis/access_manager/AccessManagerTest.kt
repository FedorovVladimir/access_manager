package tech.estesis.access_manager

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import tech.crabs.access_manager.entities.Function
import tech.crabs.access_manager.entities.RoleInfo
import tech.crabs.access_manager.services.FunctionRepository
import tech.crabs.access_manager.services.PermissionRepository
import tech.crabs.access_manager.services.RoleRepository
import java.util.*
import javax.inject.Inject

@MicronautTest
class AccessManagerTest : StringSpec() {

    @Value("\${auth.login}")
    private lateinit var login: String

    @Value("\${auth.password}")
    private lateinit var password: String

    @Inject
    private lateinit var accessManagerClient: AccessManagerClient

    @Inject
    private lateinit var roleRepository: RoleRepository

    @Inject
    private lateinit var functionRepository: FunctionRepository

    @Inject
    private lateinit var permissionRepository: PermissionRepository

    private lateinit var authHeader: String

    private lateinit var authHeaderBad: String

    init {
        "Инициализация" {
            authHeader = "Basic " + Base64.getEncoder().encodeToString("$login:$password".toByteArray())
            authHeaderBad = "Basic " + Base64.getEncoder().encodeToString("$login:${"bad_$password"}".toByteArray())
        }

        "В новой системе ролей нет" {
            accessManagerClient.getAllRoles(authHeader).size shouldBe 0
        }

        "В новой системе функций нет" {
            accessManagerClient.getAllFunctions(authHeader).size shouldBe 0
        }

        "Добавляем роль 'Администратор'" {
            accessManagerClient.addRole(authHeader, RoleInfo("ADMIN", "Администратор"))
        }

        "В системе есть одна роль" {
            accessManagerClient.getAllRoles(authHeader).size shouldBe 1
        }

        "Добавляем функцию 'Создание задачи'" {
            accessManagerClient.addFunction(authHeader, Function("create_task", "Создание задачи"))
        }

        "В системе есть одна функция" {
            accessManagerClient.getAllFunctions(authHeader).size shouldBe 1
        }

        "У роли 'Администратор' есть одно право" {
            val role = accessManagerClient.getAllRoles(authHeader)[0]
            val permissions = role.permissions
            permissions.shouldNotBeNull()
            permissions.size shouldBe 1
        }

        "Добавляем роль 'Оператор'" {
            accessManagerClient.addRole(authHeader, RoleInfo("OPERATOR", "Оператор"))
        }

        "Проверка при создании роли" {
            var e: HttpClientResponseException =
                shouldThrow { accessManagerClient.addRole(authHeader, RoleInfo("", "Оператор")) }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e = shouldThrow { accessManagerClient.addRole(authHeader, RoleInfo("OPERATOR", "")) }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e = shouldThrow { accessManagerClient.addRole(authHeader, RoleInfo("OPERATOR", "1")) }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "Роль с кодом OPERATOR уже существует"
            e = shouldThrow { accessManagerClient.addRole(authHeader, RoleInfo("1", "Оператор")) }
            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldBe "Роль с названием Оператор уже существует"
            e = shouldThrow { accessManagerClient.addRole(authHeader, null) }
            e.status shouldBe HttpStatus.BAD_REQUEST
        }

        "У роли 'Оператор' есть одно право" {
            val role = accessManagerClient.getAllRoles(authHeader)[1]
            val permissions = role.permissions
            permissions.shouldNotBeNull()
            permissions.size shouldBe 1
        }

        "Даем права роли 'Администратор' на функцию 'Создание заявки'" {
            var role = accessManagerClient.getAllRoles(authHeader)[0]
            val uuid = role.permissions!![0].uuid
            accessManagerClient.changePermissionByUuid(authHeader, uuid)
            role = accessManagerClient.getAllRoles(authHeader)[0]
            role.permissions!![0].has shouldBe true
        }

        "Получаем информаця для роли 'Администратор'" {
            accessManagerClient.getRoleByCode(authHeader, "ADMIN")
        }

        "Удаляем роль 'Администратор'" {
            accessManagerClient.deleteRole(authHeader, "ADMIN")
        }

        "В новой системе осталась одна роль" {
            accessManagerClient.getAllRoles(authHeader).size shouldBe 1
        }

        "Удаляем функцию 'Создание заявки'" {
            accessManagerClient.deleteFunctionByCode(authHeader, "create_task")
        }

        "В системе нет функций" {
            accessManagerClient.getAllFunctions(authHeader).size shouldBe 0
        }

        "Проверка коректности логина и пароля" {
            accessManagerClient.login(authHeader)
            shouldThrow<HttpClientResponseException> { accessManagerClient.login(authHeaderBad) }
        }
    }

    override fun afterSpec(spec: Spec) {
        permissionRepository.deleteAll()
        roleRepository.deleteAll()
        functionRepository.deleteAll()
    }
}
