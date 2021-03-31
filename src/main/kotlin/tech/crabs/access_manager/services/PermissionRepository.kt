package tech.crabs.access_manager.services

import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.repeatable.JoinSpecifications
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import tech.crabs.access_manager.entities.Permission
import tech.crabs.access_manager.entities.Role
import java.util.*

@JdbcRepository(dialect = Dialect.POSTGRES)
interface PermissionRepository : CrudRepository<Permission, UUID> {

    @JoinSpecifications(
        Join(value = "role"),
        Join(value = "function")
    )
    fun findByRoleOrderByFunction(role: Role): List<Permission>

    @JoinSpecifications(
        Join(value = "role"),
        Join(value = "function")
    )
    fun findByUuid(uuid: UUID): Permission

    @JoinSpecifications(
        Join(value = "role"),
        Join(value = "function")
    )
    override fun findAll(): List<Permission>
}
