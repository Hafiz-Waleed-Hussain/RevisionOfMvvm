package com.uwantolearn.rapidapiassignment.feature.home.di

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uwantolearn.rapidapiassignment.di.AppComponent
import com.uwantolearn.rapidapiassignment.di.ViewModelModule
import com.uwantolearn.rapidapiassignment.di.scopes.ActivityScope
import com.uwantolearn.rapidapiassignment.feature.home.HomeActivity
import dagger.Component
import dagger.Module
import dagger.Provides

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [HomeModule::class])
interface HomeComponent {
    fun inject(activity: HomeActivity)
}

@Module(includes = [ViewModelModule::class])
class HomeModule(private val context: Context) {

    @ActivityScope
    @Provides
    fun provideGridlayoutManager(): RecyclerView.LayoutManager = GridLayoutManager(context, 2)
}

