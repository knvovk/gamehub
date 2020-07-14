package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.domain.models.platform.Platform
import com.knvovk.gamehub.domain.models.platform.PlatformDb
import com.knvovk.gamehub.domain.models.platform.PlatformNet

class PlatformMapper : Mapper<PlatformDb, PlatformNet, Platform> {

    // TODO: Implement it
    override fun mapDbModel(dbModel: PlatformDb): Platform {
        return Platform.of(dbModel.id)
    }

    override fun mapNetModel(netModel: PlatformNet): Platform {
        return Platform.of(netModel.id)
    }
}