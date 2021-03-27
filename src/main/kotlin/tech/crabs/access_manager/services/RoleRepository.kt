package tech.crabs.access_manager.services

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import tech.crabs.access_manager.entities.Role

@JdbcRepository(dialect = Dialect.POSTGRES)
interface RoleRepository : CrudRepository<Role, Long> {

    fun findByCode(code: String): Role

    fun deleteByCode(code: String)

    fun findAllOrderByCode(): List<Role>

    fun existsByCode(code: String): Boolean
}
