package com.knvovk.gamehub.data.mappers

import com.knvovk.gamehub.data.REQUIREMENTS_NONE
import com.knvovk.gamehub.domain.models.requirements.Requirements
import com.knvovk.gamehub.domain.models.requirements.RequirementsDb
import com.knvovk.gamehub.domain.models.requirements.RequirementsNet

class RequirementsMapper : Mapper<RequirementsDb?, RequirementsNet?, Requirements> {

    // TODO: Implement it
    override fun fromDb(dbModel: RequirementsDb?): Requirements {
        return if (dbModel != null) {
            Requirements(
                minimum = dbModel.minimum,
                recommended = dbModel.recommended
            )
        } else REQUIREMENTS_NONE
    }

    override fun fromNet(netModel: RequirementsNet?): Requirements {
        return if (netModel != null) {
            Requirements(
                minimum = netModel.minimum,
                recommended = netModel.recommended
            )
        } else REQUIREMENTS_NONE
    }
}