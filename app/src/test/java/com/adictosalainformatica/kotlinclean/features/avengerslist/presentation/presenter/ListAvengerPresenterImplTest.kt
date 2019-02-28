package com.adictosalainformatica.kotlinclean.features.avengerslist.presentation.presenter

import androidx.lifecycle.LifecycleRegistry
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.policy.ListAvengerRepositoryPolicy
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.repository.impl.ListAvengersRepositoryImpl
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.LoadAvengersListUseCaseImpl
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Data
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Result
import io.mockk.clearAllMocks
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
    private var avengersListPresenter: ListAvengerPresenterImpl? = null
    private var listAvengersRepository: ListAvengersRepositoryImpl? = null
    private var loadAvengersListUseCase: LoadAvengersListUseCaseImpl? = null

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        listAvengersRepository = ListAvengersRepositoryImpl(listAvengerRepositoryPolicy)

        loadAvengersListUseCase = LoadAvengersListUseCaseImpl(listAvengersRepository!!)

        avengersListPresenter = ListAvengerPresenterImpl(loadAvengersListUseCase!!)
        avengersListPresenter?.attachView(view, lifecycle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
        avengersListPresenter = null
        listAvengersRepository = null
        loadAvengersListUseCase = null
    }

    @Test
    @Throws(Exception::class)
    fun stage1_loadAvengersList_calls_showErrorLoadingAvengersList_on_repository_error() {
        //Given
        every {listAvengerRepositoryPolicy.getAvengersList()} returns (null)

        //When
        avengersListPresenter?.loadAvengers()

        //Then
        verify {view.showProgress()}
        //verify {view.hideProgress()}
        verify {view.showError(any())}
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
        every {listAvengerRepositoryPolicy.getAvengersList()} returns (avengersModel)

        //When
        avengersListPresenter?.loadAvengers()

        //Then
        verify {view.showProgress()}
        verify {view.onAvengersListLoaded(avengersList)}
        verify {view.hideProgress()}
    }
}