package me.shtanko.topgithub.platform

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import me.shtanko.common.android.processing.image.ImageLoader
import me.shtanko.topgithub.AndroidApplication
import me.shtanko.topgithub.di.ApplicationComponent
import me.shtanko.topgithub.di.ApplicationModule
import me.shtanko.topgithub.navigation.Navigator
import javax.inject.Inject
import javax.inject.Named

abstract class BaseFragment : Fragment() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    protected inline fun <reified V : ViewModel> bindViewModel() =
            lazy { ViewModelProviders.of(this, viewModelFactory).get(V::class.java) }
}