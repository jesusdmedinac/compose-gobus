package com.jesusdmedinac.compose.gobus.domain.mapper

import com.jesusdmedinac.compose.gobus.data.remote.model.User as DataUser
import com.jesusdmedinac.compose.gobus.domain.model.User as DomainUser

class DomainUserToDataUserMapper {
    fun map(input: DomainUser): DataUser = with(input) {
        DataUser(
            email = email,
            type = type,
            path = path,
            password = password,
        )
    }
}
