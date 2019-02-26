package com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.presenter

import androidx.lifecycle.LifecycleRegistry
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.ListAvengerRepositoryPolicy
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.repository.impl.LisAvengersRepositoryImpl
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.LoadAvengersListUseCaseImpl
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Data
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Result
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class ListAvengerPresenterImplTest {

    private var listAvengerRepositoryPolicy = mockk<ListAvengerRepositoryPolicy>()
    private val lifecycle = LifecycleRegistry(mockk())
    private var view = mockk<AvengersListPresenterView>(relaxed = true)

    lateinit var listAvengersRepository: LisAvengersRepositoryImpl
    lateinit var avengersListPresenter: ListAvengerPresenterImpl
    lateinit var loadAvengersListUseCase: LoadAvengersListUseCaseImpl

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        listAvengersRepository = LisAvengersRepositoryImpl(listAvengerRepositoryPolicy)

        loadAvengersListUseCase = LoadAvengersListUseCaseImpl(listAvengersRepository)

        avengersListPresenter = ListAvengerPresenterImpl(loadAvengersListUseCase)
        avengersListPresenter.attachView(view, lifecycle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    @Throws(Exception::class)
    fun stage1_loadAvengersList_calls_showErrorLoadingAvengersList_on_repository_error() {
        //When
        every {listAvengerRepositoryPolicy.getAvengersList()} returns (null)
        avengersListPresenter.loadAvengers()

        //Then
        verify {view.showProgress()}
        verify {view.showError(any())}
        verify {view.hideProgress()}
    }

    @Test
    @Throws(Exception::class)
    fun stage2_loadAvengersList_calls_onAvengersListLoaded_on_repository_success() {
        //Given
        val avengersModel = AvengersModel()
        val data = Data()
        val result = Result()
        val avengersList = ArrayList<Result>()

        result.name = "3-D Man"
        result.description = "some description"
        avengersList.add(result)

        data.results = avengersList
        avengersModel.data = data

        //When
        every {listAvengerRepositoryPolicy.getAvengersList()} returns (avengersModel)
        avengersListPresenter.loadAvengers()

        //Then
        verify {view.showProgress()}
        verify {view.onAvengersListLoaded(avengersList)}
        verify {view.hideProgress()}
    }
}