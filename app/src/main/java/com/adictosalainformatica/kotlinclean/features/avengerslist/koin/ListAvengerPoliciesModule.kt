package com.adictosalainformatica.kotlinclean.features.avengerslist.koin

import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.policy.ListAvengerRepositoryPolicy
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.policy.impl.ListAvengerRepositoryCacheWithCloudPolicyImpl
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.policy.impl.ListAvengerRepositoryCloudWithCachePolicyImpl
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.policy.impl.ListAvengerRepositoryOnlyCloudPolicyImpl
import org.koin.dsl.module.module

val ListAvengerPoliciesModule = module(override=true) {
    single<ListAvengerRepositoryPolicy> { ListAvengerRepositoryCloudWithCachePolicyImpl(get(), get()) }
    //single<ListAvengerRepositoryPolicy> { ListAvengerRepositoryCacheWithCloudPolicyImpl(get()) }
    //single<ListAvengerRepositoryPolicy> { ListAvengerRepositoryOnlyCloudPolicyImpl(get())}
}