package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.domain.models.platformdetails.PlatformDetails
import com.knvovk.gamehub.domain.models.platformdetails.PlatformDetailsDb
import com.knvovk.gamehub.domain.models.platformdetails.PlatformDetailsNet

class PlatformDetailsMapper : Mapper<PlatformDetailsDb, PlatformDetailsNet, PlatformDetails> {

    // TODO: Implement it
    override fun mapDbModel(dbModel: PlatformDetailsDb): PlatformDetails {
        return PlatformDetails(
            platform = PlatformMapper().mapDbModel(dbModel.platform),
            releasedAt = LocalDateMapper().map(dbModel.released_at),
            requirements = RequirementsMapper().mapDbModel(dbModel.requirements_en)
        )
    }

    override fun mapNetModel(netModel: PlatformDetailsNet): PlatformDetails {
        return PlatformDetails(
            platform = PlatformMapper().mapNetModel(netModel.platform),
            releasedAt = LocalDateMapper().map(netModel.released_at),
            requirements = RequirementsMapper().mapNetModel(netModel.requirements_en)
        )
    }
}