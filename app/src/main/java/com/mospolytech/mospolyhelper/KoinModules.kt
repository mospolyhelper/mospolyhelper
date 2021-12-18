package com.mospolytech.mospolyhelper

import com.mospolytech.data.base.baseDataModule
import com.mospolytech.data.schedule.scheduleDataModule
import com.mospolytech.domain.base.baseDomainModule
import com.mospolytech.domain.schedule.scheduleDomainModule
import com.mospolytech.features.base.baseUiModule
import com.mospolytech.features.schedule.scheduleFeaturesModule

val koinModules = listOf(
    appModule,

    // Data modules
    baseDataModule,
    scheduleDataModule,

    // Domain modules
    baseDomainModule,
    scheduleDomainModule,

    // Features modules
    baseUiModule,
    scheduleFeaturesModule,
)
