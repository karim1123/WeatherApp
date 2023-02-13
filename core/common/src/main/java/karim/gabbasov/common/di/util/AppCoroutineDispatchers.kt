package karim.gabbasov.common.di.util

import kotlinx.coroutines.CoroutineDispatcher

class AppCoroutineDispatchers(
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val default: CoroutineDispatcher
)
