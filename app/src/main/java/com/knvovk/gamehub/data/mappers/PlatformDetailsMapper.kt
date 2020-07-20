package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.domain.models.platformdetails.PlatformDetails
import com.knvovk.gamehub.domain.models.platformdetails.PlatformDetailsDb
import com.knvovk.gamehub.domain.models.platformdetails.PlatformDetailsNet

class PlatformDetailsMapper : Mapper<PlatformDetailsDb, PlatformDetailsNet, PlatformDetails> {

    // TODO: Implement it
    override fun fromDb(dbModel: PlatformDetailsDb): PlatformDetails {
        return PlatformDetails(
            platform = PlatformMapper().fromDb(dbModel.platform),
            releasedAt = LocalDateMapper().map(dbModel.released_at),
            requirements = RequirementsMapper().fromDb(dbModel.requirements_en)
        )
    }

    override fun fromNet(netModel: PlatformDetailsNet): PlatformDetails {
        return PlatformDetails(
            platform = PlatformMapper().fromNet(netModel.platform),
            releasedAt = LocalDateMapper().map(netModel.released_at),
            requirements = RequirementsMapper().fromNet(netModel.requirements_en)
        )
    }
}