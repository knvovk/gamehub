package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.domain.platform.Platform
import com.knvovk.gamehub.domain.platform.PlatformDetailsResponse

class PlatformMapper : Mapper<PlatformDetailsResponse, Platform> {

    override fun map(response: PlatformDetailsResponse) = Platform.of(response.platform.id)
}