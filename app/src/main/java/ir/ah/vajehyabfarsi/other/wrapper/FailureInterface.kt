package ir.ah.vajehyabfarsi.other.wrapper

sealed interface FailureInterface

sealed class ApiCallFailure(
    open val errorMessage: String? = null,
    open val error: Exception? = null
) : FailureInterface {

    class Unauthorized(override val errorMessage: String?) :
        ApiCallFailure(errorMessage = errorMessage)

    class OtherError(override val errorMessage: String?, override val error: Exception? = null) :
        ApiCallFailure(errorMessage = errorMessage, error)

    object NoInternet : ApiCallFailure()
}