package com.adictosalainformatica.kotlinclean.features.avengerslist.koin

import com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.ListAvengerRepositoryPolicy
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.impl.ListAvengerRepositoryCloudWithCachePolicyImpl
import org.koin.dsl.module.module

val ListAvengerPoliciesModule = module(override=true) {
    single<ListAvengerRepositoryPolicy> { ListAvengerRepositoryCloudWithCachePolicyImpl(get(), get()) }
    //single<ListAvengerRepositoryPolicy> { ListAvengerRepositoryCacheWithCloudPolicyImpl(get(), get()) }
    //single<ListAvengerRepositoryPolicy> { ListAvengerRepositoryOnlyCloudPolicyImpl(get()) }
}