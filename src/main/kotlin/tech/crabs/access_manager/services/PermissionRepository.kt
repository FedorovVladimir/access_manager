package tech.crabs.access_manager.services

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import tech.crabs.access_manager.entities.Permission
import tech.crabs.access_manager.entities.Role
import java.util.*

@JdbcRepository(dialect = Dialect.POSTGRES)
interface PermissionRepository : CrudRepository<Permission, UUID> {

    fun findByRole(role: Role): List<Permission>
}
