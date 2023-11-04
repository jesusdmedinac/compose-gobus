package com.jesusdmedinac.compose.gobus.domain.mapper

import com.jesusdmedinac.compose.gobus.data.remote.model.User as DataUser
import com.jesusdmedinac.compose.gobus.domain.model.User as DomainUser

class DataUserToDomainUserMapper {
    fun map(input: DataUser): DomainUser = with(input) {
        DomainUser(
            email = email,
            type = type,
        )
    }
}
