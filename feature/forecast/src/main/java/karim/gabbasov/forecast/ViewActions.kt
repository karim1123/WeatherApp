package karim.gabbasov.forecast

internal sealed interface ViewActions {
    object CheckIsGPSEnabled : ViewActions
    object CheckIsInternetConnectionAvailable : ViewActions
    object CheckIsPermissionGranted : ViewActions
    object GoToAppSettings : ViewActions
    object GetForecast : ViewActions
}
