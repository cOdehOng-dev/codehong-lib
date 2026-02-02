package com.codehong.library.util

interface EntityMapper<Entity, Domain> {
    fun asEntity(domain: Domain): Entity
    fun asDomain(entity: Entity): Domain
}

interface DtoMapper<Dto, Domain> {
    fun asDomain(dto: Dto): Domain
}

interface DomainMapper<Dto, Domain> {
    fun asDto(domain: Domain): Dto
}
